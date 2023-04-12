package com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit

import com.slack.circuit.runtime.CircuitUiState
import com.yveskalume.circuitcasestudy.data.Fruit


sealed class HomeState(val eventSink: (HomeEvent) -> Unit) : CircuitUiState {
    data class Loading(val onEvent: (HomeEvent) -> Unit) : HomeState(onEvent)
    data class Error(
        val exception: Throwable,
        val message: String,
        val onEvent: (HomeEvent) -> Unit
    ) : HomeState(onEvent)

    data class Success(
        val fruits: List<Fruit>,
        val onEvent: (HomeEvent) -> Unit
    ) : HomeState(onEvent)
}


