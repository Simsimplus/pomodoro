package io.simsim.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun PomodoroFloat(
    modifier: Modifier = Modifier,
    appState: AppState,
) {
    val clockState = appState.clockState
    val pomodoroStateInfo by clockState.pomodoroStateInfoFlow.collectAsState(PomodoroStateInfo(Focus(25 * 60)))
    Card(modifier = modifier.fillMaxSize()) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxSize().rotate(180f),
            progress = pomodoroStateInfo.progress,
            color = MaterialTheme.colors.primary
        )
    }
}