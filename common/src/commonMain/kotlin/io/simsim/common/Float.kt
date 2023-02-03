package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.simsim.common.utils.onHovered

@Composable
fun Float(
    modifier: Modifier = Modifier,
    appState: AppState,
    onHover: () -> Unit = {},
) {
    val clockState = appState.clockState
    val pomodoroStateInfo by clockState.pomodoroStateInfoFlow.collectAsState(PomodoroStateInfo(Focus(25 * 60)))
    PomodoroTheme {
        PomodoroFloat(
            modifier = modifier
                .onHovered {
                    println("hovered")
                    onHover()
                },
            pomodoroStateInfo = pomodoroStateInfo,
        )
    }
}