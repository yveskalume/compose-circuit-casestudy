package com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic

sealed interface HomeEvent {
    object Increment : HomeEvent
    object Decrement : HomeEvent
}