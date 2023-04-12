package com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.circuit

import com.yveskalume.circuitcasestudy.data.Fruit

sealed interface AddFruitEvent {
    data class SaveFruit(val fruit: Fruit) : AddFruitEvent
    object NavigateBack : AddFruitEvent
}