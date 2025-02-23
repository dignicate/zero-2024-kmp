package com.dignicate.zero_2024_kmp.data.automobile

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface AutomobileApiClient {
    suspend fun getCompanyList(limit: Int, page: Int): List<CompanyDto>
    fun close()
}

class AutomobileApiClientImpl(private val client: HttpClient) : AutomobileApiClient {
    override suspend fun getCompanyList(limit: Int, page: Int): List<CompanyDto> {
        return client
            .get("https://freeapi.dignicate.com/automobile/v1/companies") {
                parameter("limit", limit)
                parameter("page", page)
            }
            .body()
    }

    override fun close() {
        client.close()
    }
}
