package io.simsim.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Pomodoro(
    modifier: Modifier = Modifier,
    appState: AppState,
    onClick: () -> Unit = {}
) {
    val clockState = appState.clockState
    val pomodoroStateInfo by clockState.pomodoroStateInfoFlow.collectAsState(PomodoroStateInfo(Focus(25 * 60)))
    BoxWithConstraints(
        modifier = modifier.onPointerEvent(
            eventType = PointerEventType.Enter,
        ) {
            println("hovered")
        },
        contentAlignment = Alignment.Center
    ) {
        val maxDimension = maxHeight.coerceAtMost(maxWidth)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Clock(
                modifier = Modifier.size(maxDimension).clickable(onClick = onClick),
                pomodoroStateInfo = pomodoroStateInfo
            )
        }
    }

}