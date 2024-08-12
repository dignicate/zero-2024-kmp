package com.dignicate.zero_2024_kmp.ui.spla

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dignicate.zero_2024_kmp.domain.sample.SampleUseCase
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
