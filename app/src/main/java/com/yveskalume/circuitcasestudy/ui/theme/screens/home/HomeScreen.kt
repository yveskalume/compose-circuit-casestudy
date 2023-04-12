package com.yveskalume.circuitcasestudy.ui.theme.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slack.circuit.runtime.Screen
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit.HomeEvent
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.circuit.HomeState
import kotlinx.parcelize.Parcelize

@Parcelize
object Home : Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(state: HomeState) {
    val eventSink = state.eventSink

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Add") },
                icon = { Icon(imageVector = Icons.Outlined.Add, contentDescription = null) },
                onClick = { eventSink(HomeEvent.AddNewFruit) }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is HomeState.Error -> {
                    Text(text = state.message, fontSize = 24.sp)
                }

                is HomeState.Loading -> {
                    CircularProgressIndicator()
                }

                is HomeState.Success -> {
                    if (state.fruits.isEmpty()) {
                        Text(text = "No fruit", fontSize = 24.sp)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = state.fruits, key = { it.id!! }) {
                            Card(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = it.name, fontSize = 18.sp)
                                    IconButton(onClick = { eventSink(HomeEvent.DeleteFruit(it)) }) {
                                        Icon(
                                            imageVector = Icons.Rounded.Delete,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}