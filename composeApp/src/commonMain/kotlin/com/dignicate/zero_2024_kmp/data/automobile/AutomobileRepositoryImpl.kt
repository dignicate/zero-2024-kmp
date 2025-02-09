package com.dignicate.zero_2024_kmp.data.automobile

import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileRepository
import com.dignicate.zero_2024_kmp.domain.automobile.Company
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.lighthousegames.logging.logging

class AutomobileRepositoryImpl(
    private val apiClient: AutomobileApiClient,
) : AutomobileRepository {
    override fun getAutomobileCompanyList(): Flow<Result<List<Company>>> {
        logging().d { "getAutomobileCompanyList()" }
        return callbackFlow {
            try {
                val dto = apiClient.getCompanyList()
                trySend(Result.success(dto.map { it.toDomainObject() }))
            } catch (e: Exception) {
                logging().e(e) { "Failed to get company list" }
                trySend(Result.failure(e))
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
