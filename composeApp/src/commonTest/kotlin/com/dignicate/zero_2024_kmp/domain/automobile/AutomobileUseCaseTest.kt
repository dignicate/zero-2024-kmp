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
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AutomobileUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest {
        // TODO:
        // create test scope
        // create mock repository
        // create use case
        // collect data
        // call fetch
        // assert

        assertEquals(2, 2)
    }
}
