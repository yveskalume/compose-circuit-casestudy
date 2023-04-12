package com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit

import com.yveskalume.circuitcasestudy.data.Fruit

sealed interface HomeEvent {
    object AddNewFruit : HomeEvent
    data class DeleteFruit(val fruit: Fruit) : HomeEvent
}