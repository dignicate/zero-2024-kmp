package com.dignicate.zero_2024_kmp.data.sample

import com.dignicate.zero_2024_kmp.domain.sample.SampleRepository
import com.dignicate.zero_2024_kmp.domain.sample.Todo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class SampleRepositoryImpl(private val sampleApiClient: SampleApiClient) : SampleRepository {
    override fun getTodoData(): Flow<Result<Todo>> {
        return callbackFlow {
            val job = launch {
                try {
                    val todoDto = sampleApiClient.getTodoData()
                    val todo = todoDto.toDomain()
                    trySend(Result.success(todo))
                } catch (e: Exception) {
                    trySend(Result.failure(e))
                }
            }
            awaitClose { job.cancel() }
        }
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
