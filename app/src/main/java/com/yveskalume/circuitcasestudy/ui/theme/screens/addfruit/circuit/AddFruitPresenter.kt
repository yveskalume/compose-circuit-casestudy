package com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.circuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.yveskalume.circuitcasestudy.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddFruitPresenter(): AddFruitState {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    val eventSink: (AddFruitEvent) -> Unit = { event ->
        when (event) {
            AddFruitEvent.NavigateBack -> {}
            is AddFruitEvent.SaveFruit -> {
                coroutineScope.launch(Dispatchers.IO) {
                    isLoading = true
                    AppDatabase.fruitDao(context).insert(event.fruit)
                    isLoading = false
                }
            }
        }
    }
    return when {
        isLoading -> AddFruitState.Loading(eventSink)
        else -> AddFruitState.Initial(eventSink)
    }
}