package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class ClockState(private val totalSeconds: Long, private val tickRate: Long = 1) {
    private var pomodoroTimer = PomodoroTimer()
    var pomodoroStateInfoFlow = pomodoroTimer.pomodoroStateInfoFlow

    fun pauseTimer() {
        pomodoroTimer.isPaused = true
    }

    fun resumeTimer() {
        pomodoroTimer.isPaused = false
    }

    fun switchTimer() {
        pomodoroTimer.isPaused = !pomodoroTimer.isPaused
    }
}


@Composable
fun rememberClockState(totalSeconds: Long, tickRate: Long = 1) = remember(totalSeconds, tickRate) {
    ClockState(totalSeconds, tickRate)
}
