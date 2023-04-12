package com.yveskalume.circuitcasestudy.factory

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.Home
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.HomeScreen
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit.HomeState

class ScreenFactory() : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is Home -> ui<HomeState> { state, _ -> HomeScreen(state) }
            else -> null
        }
    }
}