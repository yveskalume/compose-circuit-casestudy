package com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic

sealed interface HomeEvent {
    object AddNewFruit : HomeEvent
    object DeleteFruit : HomeEvent
}