package com.dignicate.zero_2024_kmp.domain

sealed class Resource {
    data object Initialized : Resource()
    data object Loading : Resource()
    data class Success<T>(val data: T) : Resource()
    data class Error(val message: String, val throwable: Throwable) : Resource()
}
