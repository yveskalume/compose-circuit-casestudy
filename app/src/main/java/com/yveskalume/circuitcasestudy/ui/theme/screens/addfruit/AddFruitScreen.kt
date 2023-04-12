package com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.Screen
import com.yveskalume.circuitcasestudy.data.Fruit
import com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.circuit.AddFruitEvent
import com.yveskalume.circuitcasestudy.ui.theme.screens.addfruit.circuit.AddFruitState
import kotlinx.parcelize.Parcelize

@Parcelize
object AddFruit : Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFruitScreen(state: AddFruitState) {
    val eventSink = state.eventSink

    var name by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = name, onValueChange = { name = it },
            label = {
                Text(text = "Fruit Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val newFruit = Fruit(name = name)
                eventSink(
                    AddFruitEvent.SaveFruit(newFruit)
                )
            },
            enabled = name.isNotBlank() && state !is AddFruitState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }
}