package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileUseCase
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

class AutomobileCompanyListViewModel(
    private val useCase: AutomobileUseCase,
) : ViewModel() {

    fun onCreate() {
        logging().d { "onCreate()" }
        viewModelScope.launch {
            useCase.data.collect {
                logging().d { "data: $it" }
            }
        }
    }

    fun onResume() {
        logging().d { "onResume()" }
        viewModelScope.launch {
            useCase.fetch(limit = 10, page = 1)
        }
    }
}