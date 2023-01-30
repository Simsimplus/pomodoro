package io.simsim.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.Duration

@Composable
fun Clock(
    modifier: Modifier = Modifier,
    remainDuration: Duration = Duration.ofMinutes(3),
    totalDuration: Duration = Duration.ofMinutes(5)
) {
    require(totalDuration >= remainDuration) {
        "剩余时间必须小于总时间"
    }
    val percent = remainDuration.seconds / totalDuration.seconds.toFloat()
    Box(modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = modifier.fillMaxSize(),
            progress = percent
        )
        Text(remainDuration.format())
    }
}

private fun Duration.format() = "${toMinutes().mod(60)}:${seconds.mod(60)}"