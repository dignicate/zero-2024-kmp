package com.dignicate.zero_2024_kmp.domain.automobile

import kotlinx.coroutines.flow.Flow

interface AutomobileRepository {
    fun getAutomobileCompanyList(limit: Int, page: Int): Flow<Result<List<Company>>>
}