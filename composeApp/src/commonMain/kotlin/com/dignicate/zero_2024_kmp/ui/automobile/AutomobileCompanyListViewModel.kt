package com.dignicate.zero_2024_kmp.ui.automobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dignicate.zero_2024_kmp.domain.Cursor
import com.dignicate.zero_2024_kmp.domain.Error
import com.dignicate.zero_2024_kmp.domain.Resource
import com.dignicate.zero_2024_kmp.domain.automobile.AutomobileUseCase
import com.dignicate.zero_2024_kmp.domain.automobile.Company
import com.dignicate.zero_2024_kmp.util.logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AutomobileCompanyListViewModel(
    private val useCase: AutomobileUseCase,
) : ViewModel() {

    init {
        setupBinding()
    }

    private val _uiState = MutableStateFlow(UiState())

    val uiState = _uiState.asStateFlow()

    private val limit = 10

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
            useCase.fetch(limit = limit, cursor = Cursor.First)
        }
    }

    fun onScrollEnd() {
        val uiState = _uiState.value
        if (uiState.isLoading) {
            return
        }
        viewModelScope.launch {
            useCase.fetch(limit = limit, cursor = uiState.nextCursor)
        }
    }

    private fun setupBinding() {
        viewModelScope.launch {
            useCase.data.collect { resourceWithParam ->
                val resource = resourceWithParam.resource
                when (resource) {
                    is Resource.Initialized -> {
                        // nothing.
                    }
                    is Resource.InProgress -> {
                        _uiState.value = _uiState.value.inProgress()
                    }
                    is Resource.Success -> {
                        val nextCursor = resourceWithParam.nextCursor ?: Cursor.End
                        _uiState.value = _uiState.value.success(
                            nextCursor = nextCursor,
                            data = resource.data,
                        )
                    }
                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.error(resource.error)
                    }
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val nextCursor: Cursor<Int> = Cursor.First,
        val data: List<Company> = emptyList(),
    ) {

        fun success(
            nextCursor: Cursor<Int>,
            data: List<Company>
        ): UiState {
            return copy(
                isLoading = false,
                nextCursor = nextCursor,
                data = this.data + data,
            )
        }

        fun inProgress(): UiState {
            return copy(isLoading = true)
        }

        fun error(error: Error): UiState {
            return copy(
                isLoading = false,
                data = emptyList()
            )
        }
    }
}