package com.dignicate.zero_2024_kmp.ui.screen.automobile

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
    }

    fun onResume() {
        logger.d("onResume()")
        viewModelScope.launch {
            useCase.fetch(limit = limit, cursor = Cursor.First)
        }
    }

    fun onScrollNearlyEnd() {
        val uiState = _uiState.value
        if (!uiState.shouldProceedPagination) {
            return
        }
        viewModelScope.launch {
            useCase.fetch(limit = limit, cursor = uiState.nextCursor)
        }
    }

    fun onRefresh() {
        logger.d("onRefresh()")
        viewModelScope.launch {
            _uiState.value = _uiState.value.refresh()
            useCase.fetch(limit = limit, cursor = Cursor.First)
        }
    }

    private fun setupBinding() {
        viewModelScope.launch {
            useCase.data.collect { resourceWithParam ->
                when (val resource = resourceWithParam.resource) {
                    is Resource.Initialized -> {
                        // nothing.
                    }
                    is Resource.InProgress -> {
                        _uiState.value = _uiState.value.inProgress()
                    }
                    is Resource.Success -> {
                        val nextCursor = resourceWithParam.nextCursor ?: Cursor.End
                        _uiState.value = _uiState.value.success(
                            currentCursor = resourceWithParam.currentCursor,
                            nextCursor = nextCursor,
                            newData = resource.data,
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
        val isRefreshing: Boolean = false,
        val nextCursor: Cursor<Int> = Cursor.First,
        val data: List<Company> = emptyList(),
    ) {
        val shouldProceedPagination: Boolean
            get() = nextCursor != Cursor.End || isLoading

        fun success(
            currentCursor: Cursor<Int>,
            nextCursor: Cursor<Int>,
            newData: List<Company>
        ): UiState {
            return copy(
                isLoading = false,
                isRefreshing = false,
                nextCursor = nextCursor,
                data =
                    if (currentCursor.isFirst)
                        newData
                    else
                        this.data + newData,
            )
        }

        fun refresh(): UiState {
            return copy(
                isRefreshing = true,
                nextCursor = Cursor.First,
                data = emptyList()
            )
        }

        fun inProgress(): UiState {
            return copy(isLoading = true)
        }

        fun error(error: Error): UiState {
            return copy(
                isLoading = false,
                isRefreshing = false,
                data = emptyList()
            )
        }
    }
}