package io.simsim.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Clock(
    modifier: Modifier = Modifier,
    pomodoroStateInfo: PomodoroStateInfo,
) {
    require(pomodoroStateInfo.progress in (0.0..1.0)) {
        "剩余时间必须小于总时间"
    }
    Box(modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = modifier.fillMaxSize(),
            progress = pomodoroStateInfo.progress
        )
        Text(pomodoroStateInfo.remainLiteral)
    }
}
