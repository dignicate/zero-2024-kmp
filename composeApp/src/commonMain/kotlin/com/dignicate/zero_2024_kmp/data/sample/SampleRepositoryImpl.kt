package com.dignicate.zero_2024_kmp.data.sample

import com.dignicate.zero_2024_kmp.domain.sample.SampleRepository
import com.dignicate.zero_2024_kmp.domain.sample.Todo
import kotlinx.coroutines.flow.Flow

class SampleRepositoryImpl(private val apiService: ApiService) : SampleRepository {
    override fun getTodoData(): Flow<Todo> {
        throw NotImplementedError()
//        return apiService.getTodoData()
    }
}

private fun TodoDto.toDomain(): Todo {
    return Todo(
        userId = userId,
        id = id,
        title = title,
        isCompleted = completed
    )
}
