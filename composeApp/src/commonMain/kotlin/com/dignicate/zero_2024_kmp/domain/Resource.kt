package com.dignicate.zero_2024_kmp.domain

import com.dignicate.zero_2024_kmp.util.logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn

sealed class Resource<out T: Any> {
    data object Initialized : Resource<Nothing>()
    data object InProgress : Resource<Nothing>()
    data class Success<out T: Any>(val data: T) : Resource<T>()
    data class Failure<out T: Any>(val error: Error) : Resource<T>()
}

sealed interface Cursor<out T : Any> {
    data object First : Cursor<Nothing>

    data class Next<out T : Any>(val value: T) : Cursor<T>

    data object End : Cursor<Nothing>

    val cursor: T?
        get() = when (this) {
            First, End -> null
            is Next -> value
        }

    val isFirst: Boolean
        get() = when (this) {
            First -> true
            is Next -> false
            End -> false
        }
}

data class ResourceWithCursor<out R : Any, out C : Any>(
    val currentCursor: Cursor<C>,
    val nextCursor: Cursor<C>?,
    val resource: Resource<R>,
)

data class ParamWithCursor<out P : Any, out C : Any>(
    val cursor: Cursor<C>,
    val param: P,
)

fun <S: Any, T: Any> MutableSharedFlow<S>.mapToResource(
    scope: CoroutineScope,
    then: (S) -> Flow<Result<T>>,
    mapError: (Throwable) -> Error = { Error.Unknown(it) },
): StateFlow<Resource<T>> {
    return mapToResourceAsFlow(
        then,
        mapError,
    ).stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = Resource.Initialized,
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <S: Any, T: Any> MutableSharedFlow<S>.mapToResourceAsFlow(
    then: (S) -> Flow<Result<T>>,
    mapError: (Throwable) -> Error = { Error.Unknown(it) },
): Flow<Resource<T>> {
    logger.d("mapToResourceAsFlow")
    return flatMapMerge { param: S ->
        flow {
            emit(Resource.InProgress)
            try {
                then(param)
                    .collect { result: Result<T> ->
                        result.fold(
                            onSuccess = { data: T ->
                                emit(Resource.Success(data))
                            },
                            onFailure = { throwable: Throwable ->
                                emit(Resource.Failure(mapError(throwable)))
                            }
                        )
                    }
            } catch (t: Throwable) {
                logger.e(t, "Thrown in `then()` process")
                emit(Resource.Failure(mapError(t)))
            }

        }
    }
}

sealed class Error {
    data class Unknown(val throwable: Throwable) : Error()
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <S : Any, T : Any, U : Any> Flow<ParamWithCursor<S, U>>.mapToResourceFlow(
    then: (ParamWithCursor<S, U>) -> Flow<Result<T>>,
    nextCursor: (S, T, Cursor<U>) -> Cursor<U>,
    onSuccess: (S, T, Cursor<U>) -> T = { _, data, _ -> data },
    mapError: ((Throwable) -> Error)? = null,
): Flow<ResourceWithCursor<T, U>> {
    logger.d("mapToResourceFlow")
    return flatMapMerge { param: ParamWithCursor<S, U> ->
        flow {
            emit(
                ResourceWithCursor(
                    currentCursor = param.cursor,
                    nextCursor = null,
                    resource = Resource.InProgress
                )
            )
            then(param).collect { result: Result<T> ->
                result.fold(
                    onSuccess = { data: T ->
                        emit(
                            ResourceWithCursor(
                                currentCursor = param.cursor,
                                nextCursor = nextCursor(param.param, data, param.cursor),
                                resource = Resource.Success(onSuccess(param.param, data, param.cursor)),
                            )
                        )
                    },
                    onFailure = { exception ->
                        logger.d("result error: $exception")
                        val error = mapError?.invoke(exception) ?: Error.Unknown(exception)
                        emit(
                            ResourceWithCursor(
                                currentCursor = param.cursor,
                                nextCursor = null,
                                resource = Resource.Failure(error),
                            )
                        )
                    }
                )
            }
        }
    }
}

fun <S : Any, T : Any, U : Any> Flow<ParamWithCursor<S, U>>.mapToResource(
    scope: CoroutineScope,
    then: (ParamWithCursor<S, U>) -> Flow<Result<T>>,
    nextCursor: (S, T, Cursor<U>) -> Cursor<U>,
    onSuccess: (S, T, Cursor<U>) -> T = { _, data, _ -> data },
    mapError: ((Throwable) -> Error)? = null,
): StateFlow<ResourceWithCursor<T, U>> {
    logger.d("mapToResource")
    return mapToResourceFlow(
        then = then,
        nextCursor = nextCursor,
        onSuccess = onSuccess,
        mapError = mapError,
    )
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ResourceWithCursor(Cursor.First, null, Resource.Initialized),
        )
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <S : Any, T : Any, U : Any> Flow<S>.concatMapToResourceFlow(
    then1: (S) -> Flow<Result<T>>,
    then2: (S, T) -> Flow<Result<U>>,
    mapError: ((Throwable) -> Error)? = null,
): Flow<Resource<U>> {
    return flatMapMerge { param: S ->
        flow {
            emit(Resource.InProgress)
            then1(param).collect { result1 ->
                if (result1.isSuccess) {
                    val intermediateResult = result1.getOrThrow()
                    then2(param, intermediateResult).collect { result2 ->
                        if (result2.isSuccess) {
                            emit(Resource.Success(result2.getOrThrow()))
                        } else {
                            throw result2.exceptionOrNull() ?: Exception("Unknown error")
                        }
                    }
                } else {
                    throw result1.exceptionOrNull() ?: Exception("Unknown error")
                }
            }
        }.catch { exception ->
            val error = mapError?.invoke(exception) ?: Error.Unknown(exception)
            emit(Resource.Failure(error))
        }
    }
}

fun <S : Any, T : Any, U : Any> Flow<S>.concatMapToResource(
    scope: CoroutineScope,
    then1: (S) -> Flow<Result<T>>,
    then2: (S, T) -> Flow<Result<U>>,
    mapError: ((Throwable) -> Error)? = null,
): StateFlow<Resource<U>> {
    return concatMapToResourceFlow(
        then1 = then1,
        then2 = then2,
        mapError = mapError,
    )
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Resource.Initialized,
        )
}
