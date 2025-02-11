package com.dignicate.zero_2024_kmp.data.sample

import com.dignicate.zero_2024_kmp.util.logger
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*

interface SampleApiClient {
    suspend fun getTodoData(): TodoDto
}

class ApiServiceKtorImpl(private val client: HttpClient) : SampleApiClient {
    override suspend fun getTodoData(): TodoDto {
        logger.d("getTodoData()")
        return client
            .get("https://jsonplaceholder.typicode.com/todos/1")
            .body()
    }
}
