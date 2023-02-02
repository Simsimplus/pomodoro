package io.simsim.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun PomodoroFloat(
    modifier: Modifier = Modifier,
    pomodoroStateInfo: PomodoroStateInfo,
) {
    val color = when (pomodoroStateInfo.state) {
        is Break -> LocalPomodoroColors.current.breakColor
        is Focus -> LocalPomodoroColors.current.focusColor
    }
    Card(modifier = modifier.fillMaxSize()) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxSize().rotate(180f),
            progress = pomodoroStateInfo.progress,
            color = color
        )
    }
}