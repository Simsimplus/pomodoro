package io.simsim.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
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
            modifier = modifier.onPointerEvent(
                eventType = PointerEventType.Enter,
            ) {
                println("hovered")
                onHover()
            },
            pomodoroStateInfo = pomodoroStateInfo,
        )
    }
}