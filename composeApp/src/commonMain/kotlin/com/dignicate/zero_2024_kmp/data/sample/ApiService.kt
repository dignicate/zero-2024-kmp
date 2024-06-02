package com.dignicate.zero_2024_kmp.data.sample

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import org.lighthousegames.logging.logging

interface ApiService {
    suspend fun getTodoData(): TodoDto
}

class ApiServiceKtorImpl(private val client: HttpClient) : ApiService {
    override suspend fun getTodoData(): TodoDto {
        logging().d { "getTodoData()" }
        return client
            .get("https://jsonplaceholder.typicode.com/todos/1")
            .body()
    }
}
