package com.dignicate.zero_2024_kmp.data.sample

import io.ktor.client.*
import io.ktor.client.request.*

//import io.ktor.client.*
//import io.ktor.client.features.json.*
//import io.ktor.client.features.json.serializer.*
//
//val client = HttpClient {
//    install(JsonFeature) {
//        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
//            ignoreUnknownKeys = true
//        })
//    }
//}
//
//interface ApiService {
//    suspend fun getTodoData(): TodoDto
//}
//
//class ApiServiceKtorImpl(private val client: HttpClient) : ApiService {
//    override suspend fun getTodoData(): TodoDto {
//        return client.get<TodoDto>("https://jsonplaceholder.typicode.com/todos/1")
//    }
//}
