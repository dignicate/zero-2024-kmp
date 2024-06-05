package com.dignicate.zero_2024_kmp.domain.sample

import com.dignicate.zero_2024_kmp.domain.Resource
import com.dignicate.zero_2024_kmp.domain.mapToResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow

class SampleUseCase(
    private val repository: SampleRepository,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
) {

    private val fetchTrigger = MutableSharedFlow<Unit>()

    val data: StateFlow<Resource<Todo>> =
        fetchTrigger
            .mapToResource(
                scope,
                then = { repository.getTodoData() },
            )

    suspend fun fetch() {
        fetchTrigger.emit(Unit)
    }
}
