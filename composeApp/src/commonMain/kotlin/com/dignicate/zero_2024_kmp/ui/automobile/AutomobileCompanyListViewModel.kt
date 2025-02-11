package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileUseCase
import com.dignicate.zero_2024_kmp.util.logger
import kotlinx.coroutines.launch

class AutomobileCompanyListViewModel(
    private val useCase: AutomobileUseCase,
) : ViewModel() {

    fun onCreate() {
        logger.d("onCreate()")
        viewModelScope.launch {
            useCase.data.collect {
                logger.d("data: $it")
            }
        }
    }

    fun onResume() {
        logger.d("onResume()")
        viewModelScope.launch {
            useCase.fetch(limit = 10, page = 1)
        }
    }
}