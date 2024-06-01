package com.dignicate.zero_2024_kmp.data.sample

import io.ktor.client.*
import io.ktor.client.request.*




interface ApiService {
    suspend fun getExampleData(): String
}
