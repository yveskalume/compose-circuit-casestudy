package com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.circuit

import com.slack.circuit.runtime.CircuitUiState

sealed class AddFruitState(val eventSink: (AddFruitEvent) -> Unit) : CircuitUiState {
    data class Initial(val onEvent: (AddFruitEvent) -> Unit) : AddFruitState(onEvent)
    data class Loading(val onEvent: (AddFruitEvent) -> Unit) : AddFruitState(onEvent)
}