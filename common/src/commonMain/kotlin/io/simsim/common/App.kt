package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App() {
    val totalSeconds = Duration.ofMinutes(5).seconds
    val appState = rememberAppState(totalSeconds)
    val cs = rememberCoroutineScope()
    Pomodoro(
        modifier = Modifier,
        appState = appState,
    ) {
        cs.launch(Dispatchers.IO) {
            println(
                appState.getOneWord()
            )
        }
    }
}





