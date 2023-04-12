package com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.slack.circuit.runtime.Navigator
import com.yveskalume.circuitcasestudy.data.AppDatabase
import com.yveskalume.circuitcasestudy.data.Fruit
import com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.AddFruit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@Composable
fun HomePresenter(navigator: Navigator): HomeState {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val coroutineScope = rememberCoroutineScope()

    var error: Throwable? by remember {
        mutableStateOf(null)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var fruits: List<Fruit> by remember {
        mutableStateOf(emptyList())
    }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            coroutineScope.launch(Dispatchers.IO) {
                delay(2000) // simulate loading
                isLoading = true
                AppDatabase.fruitDao(context).getAll()
                    .catch {
                        isLoading = false
                        error = it
                    }
                    .collect {
                        isLoading = false
                        error = null
                        fruits = it
                    }
            }
        }
    }

    val eventSink: (HomeEvent) -> Unit = { event ->
        when (event) {
            HomeEvent.AddNewFruit -> {
                navigator.goTo(AddFruit)
            }

            is HomeEvent.DeleteFruit -> {
                coroutineScope.launch(Dispatchers.IO) {
                    AppDatabase.fruitDao(context).delete(fruit = event.fruit)
                }
            }
        }
    }

    return when {
        isLoading -> {
            HomeState.Loading(onEvent = eventSink)
        }

        error != null -> {
            HomeState.Error(
                exception = error!!,
                message = error?.message!!,
                onEvent = eventSink
            )
        }

        else -> {
            HomeState.Success(fruits = fruits, onEvent = eventSink)
        }
    }
}