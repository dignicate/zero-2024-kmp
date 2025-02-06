package com.dignicate.zero_2024_kmp.domain.sample

import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    fun getTodoData(): Flow<Result<Todo>>
}
