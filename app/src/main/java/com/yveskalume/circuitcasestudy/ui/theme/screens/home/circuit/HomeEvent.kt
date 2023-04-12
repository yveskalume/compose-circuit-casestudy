package com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit

sealed interface HomeEvent {
    object AddNewFruit : HomeEvent
    object DeleteFruit : HomeEvent
}