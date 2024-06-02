package com.dignicate.zero_2024_kmp.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

sealed class Resource<out T> {
    data object Initialized : Resource<Nothing>()
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val error: Error) : Resource<T>()
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <S, T> Flow<S>.mapToResource(
    scope: CoroutineScope,
    then: (S) -> Flow<T>,
    mapError: (Throwable) -> Error = { Error.Unknown(it) },
): StateFlow<Resource<T>> {
    return this
        .flatMapLatest { then(it) }
        .map { Resource.Success(it) }
        .onStart { emit(Resource.Loading<T>()) }
        .catch { emit(Resource.Failure(mapError(it))) }
        .stateIn(scope, SharingStarted.Eagerly, Resource.Initialized)
}

sealed class Error {
    data class Unknown(val throwable: Throwable) : Error()
}
