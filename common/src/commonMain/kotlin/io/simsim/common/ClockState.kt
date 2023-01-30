package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.time.Duration

class ClockState(private val totalDuration: Duration, private val tickRate: Long = 1) {
    val remain = flow {
        println("new timer")
        var remain = totalDuration.seconds
        while (remain > 0) {
            remain--
            emit(Duration.ofSeconds(remain))
            delay(tickRate * 1000)
        }
    }
}

@Composable
fun rememberClockState(totalDuration: Duration, tickRate: Long = 1) = remember(totalDuration, tickRate) {
    ClockState(totalDuration, tickRate)
}
