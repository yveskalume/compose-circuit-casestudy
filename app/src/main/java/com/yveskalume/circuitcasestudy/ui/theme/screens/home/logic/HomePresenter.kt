package com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.presenter.presenterOf
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.Home

@Composable
fun HomePresenter(): HomeState {
    var count by remember { mutableStateOf(1) }
    return HomeState(count) { event ->
        when (event) {
            HomeEvent.Increment -> count++
            HomeEvent.Decrement -> count--
        }
    }
}
class HomePresenterFactory() : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is Home -> presenterOf { HomePresenter() }
            else -> null
        }
    }
}