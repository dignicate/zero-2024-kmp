package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileUseCase
import com.dignicate.zero_2024_kmp.domain.sample.SampleUseCase
import kotlinx.coroutines.launch

class AutomobileCompanyListViewModel(
    private val useCase: AutomobileUseCase,
) : ViewModel() {

    fun onCreate() {
        println("onCreate()")
    }

    fun onResume() {
        println("onResume()")
    }
}