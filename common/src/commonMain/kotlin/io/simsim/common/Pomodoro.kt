package io.simsim.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.Duration

@Composable
fun Pomodoro(
    remainDuration: Duration = Duration.ofMinutes(3),
    totalDuration: Duration = Duration.ofMinutes(5),
    onClick: () -> Unit = {}
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center
    ) {
        val maxDimension = maxHeight.coerceAtMost(maxWidth)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Clock(
                Modifier.size(maxDimension).clickable(onClick = onClick), remainDuration, totalDuration
            )
        }
    }

}