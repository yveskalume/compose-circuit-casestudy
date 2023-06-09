package com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.circuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.slack.circuit.runtime.Navigator
import com.yveskalume.circuitcasestudy.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddFruitPresenter(navigator: Navigator): AddFruitState {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    val eventSink: (AddFruitEvent) -> Unit = { event ->
        when (event) {
            AddFruitEvent.NavigateBack -> {
                navigator.pop()
            }
            is AddFruitEvent.SaveFruit -> {
                coroutineScope.launch(Dispatchers.IO) {
                    isLoading = true
                    delay(2000) // simulate loading
                    AppDatabase.fruitDao(context).insert(event.fruit)
                    isLoading = false
                    navigator.pop()
                }
            }
        }
    }
    return when {
        isLoading -> AddFruitState.Loading(eventSink)
        else -> AddFruitState.Initial(eventSink)
    }
}