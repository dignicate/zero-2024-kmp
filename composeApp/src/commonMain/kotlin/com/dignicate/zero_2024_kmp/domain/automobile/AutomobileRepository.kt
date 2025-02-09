package com.dignicate.zero_2024_kmp.domain.automobile

import kotlinx.coroutines.flow.Flow

interface AutomobileRepository {
    fun getAutomobileCompanyList(): Flow<Result<List<Company>>>
}