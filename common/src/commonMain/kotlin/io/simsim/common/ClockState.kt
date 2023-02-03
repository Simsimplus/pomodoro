package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.onEach

class ClockState(autoStart: String = "true", onStateChange: (PomodoroStateInfo) -> Unit) {
    private var pomodoroTimer = PomodoroTimer(autoStart == "true")
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
fun rememberClockState(autoStart: String, onStateChange: (PomodoroStateInfo) -> Unit) = remember(onStateChange) {
    ClockState(autoStart, onStateChange)
}
