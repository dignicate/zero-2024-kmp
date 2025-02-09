package com.dignicate.zero_2024_kmp.data.automobile

import io.ktor.client.HttpClient

interface AutomobileApiClient {
    suspend fun getCompanyList(): List<CompanyDto>
}

class AutomobileApiClientImpl(private val client: HttpClient) : AutomobileApiClient {
    override suspend fun getCompanyList(): List<CompanyDto> {
        throw NotImplementedError()
    }
}

