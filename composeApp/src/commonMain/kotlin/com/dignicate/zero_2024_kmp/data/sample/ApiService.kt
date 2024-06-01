package com.dignicate.zero_2024_kmp.data.sample

import io.ktor.client.*
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

//import io.ktor.client.features.json.*
//import io.ktor.client.features.json.serializer.*

//val client = HttpClient {
//    install(Json) {
//        serializer = KotlinxSerializer(KxJson {
//            ignoreUnknownKeys = true
//        })
//    }
//}

interface ApiService {
    suspend fun getTodoData(): TodoDto
}

class ApiServiceKtorImpl(private val client: HttpClient) : ApiService {
    override suspend fun getTodoData(): TodoDto {
        return client.get("https://jsonplaceholder.typicode.com/todos/1")
    }
}
