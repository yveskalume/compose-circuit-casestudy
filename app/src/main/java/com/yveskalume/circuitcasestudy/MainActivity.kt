package com.yveskalume.circuitcasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.CircuitContent
import com.yveskalume.circuitcasestudy.ui.theme.CircuitCaseStudyTheme
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.Home
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.logic.HomePresenterFactory
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.HomeScreenUiFactory

class MainActivity : ComponentActivity() {

    private val circuitConfig = CircuitConfig.Builder()
        .addUiFactory(HomeScreenUiFactory())
        .addPresenterFactory(HomePresenterFactory())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircuitCaseStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CircuitCompositionLocals(circuitConfig) { CircuitContent(Home) }
                }
            }
        }
    }
}