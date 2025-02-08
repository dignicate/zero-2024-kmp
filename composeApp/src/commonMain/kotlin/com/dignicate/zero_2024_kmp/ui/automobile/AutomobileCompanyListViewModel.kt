package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dignicate.zero_2024_kmp.domain.sample.SampleUseCase
import kotlinx.coroutines.launch

class AutomobileCompanyListViewModel(

) : ViewModel() {

    fun onCreate() {
        println("onCreate")
    }

    fun onResume() {
        println("onResume()")
    }
}