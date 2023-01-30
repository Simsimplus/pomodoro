package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration

@Composable
fun App() {
    val totalDuration = Duration.ofMinutes(5)
    val appState = rememberAppState(totalDuration)
    val clockState = appState.clockState
    val remainDuration by clockState.remain.collectAsState(Duration.ZERO)
    val cs = rememberCoroutineScope()
    Pomodoro(
        remainDuration, totalDuration
    ) {
        cs.launch(Dispatchers.IO) {
            println(
                appState.getOneWord()
            )
        }
    }

}





