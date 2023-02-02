package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun App(
    appState: AppState
) {
    val clockState = appState.clockState
    val pomodoroStateInfo by clockState.pomodoroStateInfoFlow.collectAsState(PomodoroStateInfo(Focus(25 * 60)))
    val cs = rememberCoroutineScope()
    PomodoroTheme {
        Pomodoro(
            modifier = Modifier,
            pomodoroStateInfo = pomodoroStateInfo,
        ) {
            cs.launch(Dispatchers.IO) {
                println(
                    appState.getOneWord()
                )
            }
        }
    }
}





