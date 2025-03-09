package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetDeviceIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DeviceViewModel(
    private val getDeviceIdUseCase: GetDeviceIdUseCase,
) : KmmViewModel() {

    private val _uiState = MutableStateFlow(0L)
    val uiState: StateFlow<Long> = _uiState

    init {
        fetchDeviceId()
    }

    private fun fetchDeviceId() {
        viewModelScope.launch {
            getDeviceIdUseCase()
                .catch {
                    _uiState.tryEmit(0)
                }
                .collect { deviceId ->
                    _uiState.tryEmit(deviceId)
                }
        }
    }
}