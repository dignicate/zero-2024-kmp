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
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class AutomobileUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        setTestLogger()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest(testDispatcher) {
        val repository = AutomobileRepositoryMock(
            mockGetAutomobileRepository = { limit, cursor ->
                val data = when (cursor) {
                    Cursor.First -> listOf(
                        Company(
                            id = Company.Id(1),
                            name = "company1",
                            country = "Japan",
                            foundedYear = 2000,
                        ),
                    )
                    is Cursor.Next ->  {
                        println("cursor.value = ${cursor.value}")
                        when (cursor.value) {
                            2 -> listOf(
                                Company(
                                    id = Company.Id(2),
                                    name = "company2",
                                    country = "USA",
                                    foundedYear = 2001,
                                ),
                            )
                            3 -> listOf(
                                Company(
                                    id = Company.Id(3),
                                    name = "company3",
                                    country = "Germany",
                                    foundedYear = 2002,
                                ),
                            )
                            else -> emptyList()
                        }
                    }
                    is Cursor.End -> emptyList()
                }
                Result.success(data)
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
                    println("----")
                }
                .onEach {
                    println(it)
                    results.add(it)
                }
                .onCompletion {
                    println("----")
                }
                .collect()
        }
        advanceTimeBy(2)
        useCase.fetch(10, Cursor.First)
        advanceTimeBy(2)
        useCase.fetch(10, Cursor.Next(2))
        advanceTimeBy(2)
        useCase.fetch(10, Cursor.Next(3))
        advanceTimeBy(2)
        useCase.fetch(10, Cursor.Next(4))
        advanceTimeBy(2)

        assertEquals(9, results.size)
        results[0].let {
            assertEquals(Resource.Initialized, it.resource)
            assertEquals(Cursor.First, it.currentCursor)
            assertEquals(null, it.nextCursor)
        }
        results[1].let {
            assertEquals(Resource.InProgress, it.resource)
            assertEquals(Cursor.First, it.currentCursor)
            assertEquals(null, it.nextCursor)
        }
        results[2].let {
            assertTrue(it.resource is Resource.Success)
            assertEquals("Japan", (it.resource as Resource.Success).data[0].country)
            assertEquals(Cursor.First, it.currentCursor)
            assertEquals(Cursor.Next(2), it.nextCursor)
        }
        results[3].let {
            assertEquals(Resource.InProgress, it.resource)
            assertEquals(Cursor.Next(2), it.currentCursor)
            assertEquals(null, it.nextCursor)
        }
        results[4].let {
            assertTrue(it.resource is Resource.Success)
            assertEquals("USA", (it.resource as Resource.Success).data[0].country)
            assertEquals(Cursor.Next(2), it.currentCursor)
            assertEquals(Cursor.Next(3), it.nextCursor)
        }
        results[5].let {
            assertEquals(Resource.InProgress, it.resource)
            assertEquals(Cursor.Next(3), it.currentCursor)
            assertEquals(null, it.nextCursor)
        }
        results[6].let {
            assertTrue(it.resource is Resource.Success)
            assertEquals("Germany", (it.resource as Resource.Success).data[0].country)
            assertEquals(Cursor.Next(3), it.currentCursor)
            assertEquals(Cursor.Next(4), it.nextCursor)
        }
        results[7].let {
            assertEquals(Resource.InProgress, it.resource)
            assertEquals(Cursor.Next(4), it.currentCursor)
            assertEquals(null, it.nextCursor)
        }
        results[8].let {
            assertTrue(it.resource is Resource.Success)
            assertTrue((it.resource as Resource.Success).data.isEmpty())
            assertEquals(Cursor.Next(4), it.currentCursor)
            assertEquals(Cursor.End, it.nextCursor)
        }
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


