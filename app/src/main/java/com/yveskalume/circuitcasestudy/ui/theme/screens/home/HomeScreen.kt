package com.yveskalume.circuitcasestudy.ui.theme.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic.HomeEvent
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic.HomeState
import kotlinx.parcelize.Parcelize

@Parcelize
object Home : Screen
class HomeScreenUiFactory() : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is Home -> ui<HomeState> { state, _ -> HomeScreen(state) }
            else -> null
        }
    }
}

@Composable
fun HomeScreen(state: HomeState) {
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