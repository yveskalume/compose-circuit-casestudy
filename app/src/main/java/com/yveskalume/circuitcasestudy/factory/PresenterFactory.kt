package com.yveskalume.circuitcasestudy.factory

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.presenter.presenterOf
import com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.AddFruit
import com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.circuit.AddFruitPresenter
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.Home
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit.HomePresenter

class PresenterFactory() : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is Home -> presenterOf { HomePresenter() }
            is AddFruit -> presenterOf { AddFruitPresenter() }
            else -> null
        }
    }
}