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
import kotlinx.coroutines.flow.stateIn

sealed class Resource<out T: Any> {
    data object Initialized : Resource<Nothing>()
    data object InProgress : Resource<Nothing>()
    data class Success<out T: Any>(val data: T) : Resource<T>()
    data class Failure<out T: Any>(val error: Error) : Resource<T>()
}

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
