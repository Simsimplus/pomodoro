package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.onEach

class ClockState(onStateChange: (PomodoroStateInfo) -> Unit) {
    private var pomodoroTimer = PomodoroTimer()
    var pomodoroStateInfoFlow = pomodoroTimer.pomodoroStateInfoFlow.onEach(onStateChange)

    fun pauseTimer() {
        pomodoroTimer.isPaused = true
    }

    fun resumeTimer() {
        pomodoroTimer.isPaused = false
    }

    fun switchTimer() {
        pomodoroTimer.isPaused = !pomodoroTimer.isPaused
    }

    fun resetTimer() {
        pomodoroTimer.reset()
    }
}


@Composable
fun rememberClockState(onStateChange: (PomodoroStateInfo) -> Unit) = remember(onStateChange) {
    ClockState(onStateChange)
}
