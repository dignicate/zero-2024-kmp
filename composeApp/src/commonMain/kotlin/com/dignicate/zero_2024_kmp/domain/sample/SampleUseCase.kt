package com.dignicate.zero_2024_kmp.domain.sample

import kotlinx.coroutines.flow.MutableSharedFlow

class SampleUseCase(private val repository: SampleRepository) {

    private val fetchTrigger = MutableSharedFlow<Unit>()

}