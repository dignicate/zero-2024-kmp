package com.dignicate.zero_2024_kmp.domain.automobile

import com.dignicate.zero_2024_kmp.domain.Cursor
import com.dignicate.zero_2024_kmp.domain.ParamWithCursor
import com.dignicate.zero_2024_kmp.domain.ResourceWithCursor
import com.dignicate.zero_2024_kmp.domain.mapToResource
import com.dignicate.zero_2024_kmp.util.logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce

class AutomobileUseCase(
    private val repository: AutomobileRepository,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
) {
    private val fetchTrigger =
        MutableSharedFlow<ParamWithCursor<FetchParams, Int>>()

    val data: StateFlow<ResourceWithCursor<List<Company>, Int>> =
        fetchTrigger
            .debounce(300)
            .mapToResource(
                scope = scope,
                then = {
                    repository.getAutomobileCompanyList(it.param.limit, it.cursor)
                },
                nextCursor = { _, data, cursor ->
                    if (data.isEmpty()) {
                        Cursor.End
                    } else {
                        when (cursor) {
                            Cursor.End -> Cursor.End
                            Cursor.First -> Cursor.Next(2)
                            is Cursor.Next -> Cursor.Next(cursor.value + 1)
                        }
                    }
                },
            )

    suspend fun fetch(limit: Int, cursor: Cursor<Int>) {
        logger.v("fetch(limit = $limit, cursor = $cursor)")
        fetchTrigger.emit(
            ParamWithCursor(
                cursor = cursor,
                param = FetchParams(limit = limit)
            )
        )
    }

    data class FetchParams(
        val limit: Int,
    )
}