package com.yveskalume.circuitcasestudy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.presenter.presenterOf
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import com.yveskalume.circuitcasestudy.Home.HomeEvent
import kotlinx.parcelize.Parcelize

@Composable
fun HomeScreen(state: Home.HomeState) {
    val onEvent = state.onEvent

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Count: ${state.count}",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = { onEvent(HomeEvent.Increment) }
        ) {
            Text(text = "Increment")
        }
        Button(
            onClick = { onEvent(HomeEvent.Decrement) }
        ) {
            Text(text = "Decrement")
        }
    }
}

@Parcelize
object Home : Screen {
    data class HomeState(
        val count: Int,
        val onEvent: (HomeEvent) -> Unit
    ) : CircuitUiState

    sealed interface HomeEvent {
        object Increment : HomeEvent
        object Decrement : HomeEvent
    }
}

class HomeScreenUiFactory() : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is Home -> ui<Home.HomeState> { state, _ -> HomeScreen(state) }
            else -> null
        }
    }
}


@Composable
fun HomePresenter(): Home.HomeState {
    var count by remember { mutableStateOf(1) }
    return Home.HomeState(count) { event ->
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