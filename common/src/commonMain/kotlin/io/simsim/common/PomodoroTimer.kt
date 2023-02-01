package io.simsim.common

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class PomodoroTimer {
    private val cycle = listOf(
        Focus(25 * 60),
        Break(5 * 60),
        Focus(25 * 60),
        Break(5 * 60),
        Focus(25 * 60),
        Break(15 * 60),
    )

    @Volatile
    var isPaused: Boolean = false
    val pomodoroStateInfoFlow = flow {
        var stateIndex = 0
        while (true) {
            val state = cycle[stateIndex.mod(cycle.size)]
            state.reset()
            while (state.remain > 0) {
                delay(1000)
                if (!isPaused) {
                    state.remain--
                }
                emit(
                    PomodoroStateInfo(state)
                )
            }
            // cycled
            stateIndex++


        }

    }
}