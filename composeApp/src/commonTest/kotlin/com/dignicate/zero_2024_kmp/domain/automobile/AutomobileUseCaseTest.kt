package com.dignicate.zero_2024_kmp.domain.automobile

import com.dignicate.zero_2024_kmp.domain.Cursor
import com.dignicate.zero_2024_kmp.domain.ParamWithCursor
import com.dignicate.zero_2024_kmp.domain.ResourceWithCursor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AutomobileUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test(): Unit = runTest {

    }
}

//@OptIn(ExperimentalCoroutinesApi::class)
//class AutomobileUseCaseTest {
//
//    private lateinit var useCase: AutomobileUseCase
//    private lateinit var repository: FakeAutomobileRepository
//    private lateinit var fetchTrigger: MutableSharedFlow<ParamWithCursor<AutomobileUseCase.FetchParams, Int>>
//    private lateinit var data: StateFlow<ResourceWithCursor<List<Company>, Int>>
//    private lateinit var testScope: TestScope
//
//    @Before
//    fun setUp() {
//        repository = FakeAutomobileRepository()
//        fetchTrigger = MutableSharedFlow()
//        testScope = TestScope()
//        useCase = AutomobileUseCase(repository, TestCoroutineScope(Dispatchers.Default))
//        data = useCase.data
//    }
//
//    @Test
//    fun `fetch should emit data when called`() = runTest {
//        val limit = 10
//        val cursor = Cursor.First
//
//        useCase.fetch(limit, cursor)
//
//        val result = data.first()
//        assertEquals(ResourceWithCursor.Initialized, result.resource)
//    }
//
//    @Test
//    fun `fetch should not emit data when cursor is Cursor_End`() = runTest {
//        val limit = 10
//        val cursor = Cursor.End
//
//        useCase.fetch(limit, cursor)
//
//        val result = data.first()
//        assertEquals(ResourceWithCursor.Initialized, result.resource)
//    }
//}