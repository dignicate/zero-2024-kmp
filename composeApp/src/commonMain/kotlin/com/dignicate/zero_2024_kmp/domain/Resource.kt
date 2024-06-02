package com.dignicate.zero_2024_kmp.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

sealed class Resource {
    data object Initialized : Resource()
    data object Loading : Resource()
    data class Success<T>(val data: T) : Resource()
    data class Error(val message: String, val throwable: Throwable) : Resource()
}

fun Flow<T>.mapToResource(
    scope: CoroutineScope,
    mapError: () ->
): StateFlow<T> {
    return map { Resource.Success(it) }
        .onStart { emit(Resource.Loading) }
        .catch { emit(Resource.Error(it.message ?: "Unknown error", it)) }
        .stateIn(scope)
}
