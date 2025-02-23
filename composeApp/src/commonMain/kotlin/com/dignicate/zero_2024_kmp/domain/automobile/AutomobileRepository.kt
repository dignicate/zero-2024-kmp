package com.dignicate.zero_2024_kmp.domain.automobile

import com.dignicate.zero_2024_kmp.domain.Cursor
import kotlinx.coroutines.flow.Flow

interface AutomobileRepository {
    fun getAutomobileCompanyList(limit: Int, cursor: Cursor<Int>): Flow<Result<List<Company>>>
}