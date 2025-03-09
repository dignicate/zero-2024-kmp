package com.dignicate.zero_2024_kmp.domain.automobile

import com.dignicate.zero_2024_kmp.domain.Cursor
import com.dignicate.zero_2024_kmp.domain.Resource
import com.dignicate.zero_2024_kmp.domain.ResourceWithCursor
import com.dignicate.zero_2024_kmp.util.setTestLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AutomobileUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    private val testData: List<Company> = listOf(
        Company(
            id = Company.Id(1),
            name = "company1",
            country = "Japan",
            foundedYear = 2000,
        ),
    )

    @BeforeTest
    fun setUp() {
        setTestLogger()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest {
        val repository = AutomobileRepositoryMock(
            mockGetAutomobileRepository = { limit, cursor ->
                println("mock")
                if (cursor == Cursor.First) {
                    Result.success(testData)
                } else {
                    Result.success(emptyList())
                }
            }
        )
        val results = mutableListOf<ResourceWithCursor<List<Company>, Int>>()
        val useCase = AutomobileUseCase(
            repository = repository,
            scope = CoroutineScope(testDispatcher),
        )
        backgroundScope.launch {
            useCase.data
                .onSubscription {
                    println("onSubscription")
                }
                .onEach {
                    println(it)
                    results.add(it)
                }
                .onCompletion {
                    println("onCompletion")
                }
                .collect()
        }
        advanceTimeBy(20)

        useCase.fetch(10, Cursor.First)
        advanceTimeBy(9)
        useCase.fetch(10, Cursor.Next(2))
        advanceTimeBy(9)

        assertEquals(3, results.size)
        results[0].let {
            assertEquals(Resource.Initialized, it.resource)
            assertEquals(Cursor.First, it.currentCursor)
            assertEquals(null, it.nextCursor)
        }
//        assertIs(Resource.)
//        assertEquals(2, 2)
    }

    class AutomobileRepositoryMock(
        private val mockGetAutomobileRepository: (limit: Int, cursor: Cursor<Int>) -> Result<List<Company>>,
    ) : AutomobileRepository {
        override fun getAutomobileCompanyList(
            limit: Int,
            cursor: Cursor<Int>
        ): Flow<Result<List<Company>>> {
            println("getAutomobileCompanyList()")
            return flow {
                delay(1)
                emit(mockGetAutomobileRepository(limit, cursor))
            }
        }
    }
}


