package com.yveskalume.circuitcasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.yveskalume.circuitcasestudy.factory.PresenterFactory
import com.yveskalume.circuitcasestudy.factory.ScreenFactory
import com.yveskalume.circuitcasestudy.ui.theme.CircuitCaseStudyTheme
import com.yveskalume.circuitcasestudy.ui.theme.screens.home.Home

class MainActivity : ComponentActivity() {

    private val circuitConfig = CircuitConfig.Builder()
        .addUiFactory(ScreenFactory())
        .addPresenterFactory(PresenterFactory())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val backstack = rememberSaveableBackStack { push(Home) }
            val navigator = rememberCircuitNavigator(backstack)

            CircuitCaseStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CircuitCompositionLocals(circuitConfig) {
                        NavigableCircuitContent(
                            navigator,
                            backstack
                        )
                    }
                }
            }
        }
    }
}