package com.almarpa.kmmtemplateapp.core.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.event.BaseUiEvent
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.state.BaseUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S : BaseUiState, E : BaseUiEvent>(
    initialState: S,
) : ViewModel() {

    protected val _uiState: MutableStateFlow<S> = MutableStateFlow(value = initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    protected val _uiEvent: MutableSharedFlow<E> = MutableSharedFlow()
    val uiEvent: SharedFlow<E> = _uiEvent.asSharedFlow()

    protected val _uiEffect: MutableSharedFlow<E> = MutableSharedFlow()
    val uiEffect: SharedFlow<E> = _uiEvent.asSharedFlow()
}