package com.dignicate.zero_2024_kmp.data.automobile

import com.dignicate.zero_2024_kmp.domain.Cursor
import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileRepository
import com.dignicate.zero_2024_kmp.domain.automobile.Company
import com.dignicate.zero_2024_kmp.util.logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AutomobileRepositoryImpl(
    private val apiClient: AutomobileApiClient,
) : AutomobileRepository {
    override fun getAutomobileCompanyList(limit: Int, cursor: Cursor<Int>): Flow<Result<List<Company>>> {
        logger.v("getAutomobileCompanyList()")
        return callbackFlow {
            try {
                val page = when (cursor) {
                    Cursor.End -> {
                        trySend(Result.success(emptyList()))
                        return@callbackFlow
                    }
                    Cursor.First -> 1
                    is Cursor.Next -> cursor.value
                }
                val dto = apiClient.getCompanyList(limit = limit, page = page)
                trySend(Result.success(dto.map { it.toDomainObject() }))
            } catch (e: Exception) {
                logger.e(e, "Failed to get company list")
                trySend(Result.failure(e))
            }
            awaitClose {
                apiClient.close()
            }
        }
    }
}

private fun CompanyDto.toDomainObject(): Company {
    return Company(
        id = Company.Id(value = this.id),
        name = this.name.first().value,
        country = this.country.first().value,
        foundedYear = this.foundedYear
    )
}
