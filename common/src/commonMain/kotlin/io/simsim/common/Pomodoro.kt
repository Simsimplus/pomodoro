package io.simsim.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.simsim.common.utils.onHovered

@Composable
fun Pomodoro(
    modifier: Modifier = Modifier,
    pomodoroStateInfo: PomodoroStateInfo,
    onClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier.onHovered {
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