package com.dignicate.zero_2024_kmp.data.automobile

import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileRepository
import com.dignicate.zero_2024_kmp.domain.automobile.Company
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AutomobileRepositoryImpl(
    private val apiClient: AutomobileApiClient,
) : AutomobileRepository {
    override fun getAutomobileCompanyList(): Flow<Result<List<Company>>> {
        return flow {
            try {
                val dto = apiClient.getCompanyList()
                emit(Result.success(dto.map { it.toDomainObject() }))
            } catch (e: Exception) {
                emit(Result.failure(e))
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
