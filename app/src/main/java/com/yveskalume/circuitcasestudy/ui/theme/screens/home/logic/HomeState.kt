package com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic

import com.slack.circuit.runtime.CircuitUiState

data class HomeState(
    val count: Int,
    val onEvent: (HomeEvent) -> Unit
) : CircuitUiState