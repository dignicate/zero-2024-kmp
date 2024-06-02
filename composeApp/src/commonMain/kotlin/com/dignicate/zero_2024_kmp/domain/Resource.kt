package com.dignicate.zero_2024_kmp.domain

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
        scope,
        then,
        mapError,
    ).stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Initialized,
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <S: Any, T: Any> MutableSharedFlow<S>.mapToResourceAsFlow(
    scope: CoroutineScope,
    then: (S) -> Flow<Result<T>>,
    mapError: (Throwable) -> Error = { Error.Unknown(it) },
): Flow<Resource<T>> {
    return flatMapMerge { param: S ->
        flow {
            emit(Resource.InProgress)
            then(param).collect { result: Result<T> ->
                result.fold(
                    onSuccess = { data: T ->
                        emit(Resource.Success(data))
                    },
                    onFailure = { throwable: Throwable ->
                        emit(Resource.Failure(mapError(throwable)))
                    }
                )
            }
        }
    }
}

sealed class Error {
    data class Unknown(val throwable: Throwable) : Error()
}
