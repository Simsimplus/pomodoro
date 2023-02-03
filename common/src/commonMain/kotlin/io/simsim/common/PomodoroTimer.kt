package io.simsim.common

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class PomodoroTimer(autoStart: Boolean = true) :
    CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Default) {
    private val cycle = listOf(
        Focus(25 * 60),
        Break(5 * 60),
        Focus(25 * 60),
        Break(5 * 60),
        Focus(25 * 60),
        Break(15 * 60),
    )

    @Volatile
    var isPaused: Boolean = !autoStart
    private val controlFlow = MutableStateFlow(1)
    val pomodoroStateInfoFlow = controlFlow.flatMapLatest {
        flow {
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
    }.stateIn(this, SharingStarted.Lazily, PomodoroStateInfo(Focus(25 * 60)))

    fun reset() = controlFlow.update { it + 1 }
}