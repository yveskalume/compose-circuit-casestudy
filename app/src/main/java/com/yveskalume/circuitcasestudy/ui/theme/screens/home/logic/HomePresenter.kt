package com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.yveskalume.circuitcasestudy.data.AppDatabase
import com.yveskalume.circuitcasestudy.data.Fruit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext

@Composable
fun HomePresenter(): HomeState {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var error: Throwable? by remember {
        mutableStateOf(null)
    }

    var fruits: List<Fruit> by remember {
        mutableStateOf(emptyList())
    }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.IO) {
                AppDatabase.fruitDao(context).getAll()
                    .catch {
                        error = it
                    }
                    .collect {
                        error = null
                        fruits = it
                    }
            }
        }
    }

    val eventSink: (HomeEvent) -> Unit = { event ->
        when (event) {
            HomeEvent.AddNewFruit -> {}
            HomeEvent.DeleteFruit -> {}
        }
    }

    return when {
        fruits.isEmpty() && error == null -> {
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