package com.dignicate.zero_2024_kmp.domain.automobile

import com.dignicate.zero_2024_kmp.domain.Resource
import com.dignicate.zero_2024_kmp.domain.mapToResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.lighthousegames.logging.logging

class AutomobileUseCase(
    private val repository: AutomobileRepository,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
) {
    private val fetchTrigger = MutableSharedFlow<FetchParams>()

    val data: StateFlow<Resource<List<Company>>> =
        fetchTrigger
            .mapToResource(
                scope,
                then = { repository.getAutomobileCompanyList() },
            )

    suspend fun fetch(limit: Int, page: Int) {
        logging().d { "fetch(limit = $limit, page = $page)" }
        fetchTrigger.emit(FetchParams(limit = limit, page = page))
    }

    data class FetchParams(
        val limit: Int,
        val page: Int,
    )
}