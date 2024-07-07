package com.dignicate.zero_2024_kmp.ui.spla

import com.dignicate.zero_2024_kmp.domain.sample.SampleUseCase
import com.dignicate.zero_2024_kmp.ui.ViewModel
import kotlinx.coroutines.launch

class BukiListViewModel(
    private val sampleUseCase: SampleUseCase,
) : ViewModel() {

    fun onCreate() {
        viewModelScope.launch {

        }
    }

    fun onResume() {
        viewModelScope.launch {
            sampleUseCase.fetch()
        }
    }
}
