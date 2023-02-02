package io.simsim.common

sealed class PomodoroState(val total: Long, var remain: Long = total) {
    fun reset() {
        remain = total
    }

    override fun toString(): String {
        return "PomodoroState(seconds=$total, remain=$remain)"
    }

}

class Focus(seconds: Long) : PomodoroState(seconds)

class Break(seconds: Long) : PomodoroState(seconds)

data class PomodoroStateInfo(
    val state: PomodoroState,
    val progress: Float = state.remain / state.total.toFloat(),
    val remainLiteral: String = state.remain.run { "${div(60)}:${mod(60)}" }
)
