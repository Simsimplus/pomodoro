package io.simsim.common

sealed class PomodoroState(val total: Long, var remain: Long = total) {
    fun reset() {
        remain = total
    }

    override fun toString(): String {
        return "PomodoroState(seconds=$total, remain=$remain)"
    }

}

class Focus(seconds: Long) : PomodoroState(seconds) {
    override fun equals(other: Any?): Boolean {
        return other is Focus && other.total == total && other.remain == remain
    }

    override fun hashCode(): Int {
        var result = total.hashCode()
        result = 31 * result + remain.hashCode()
        return result
    }
}

class Break(seconds: Long) : PomodoroState(seconds) {
    override fun equals(other: Any?): Boolean {
        return other is Break && other.total == total && other.remain == remain
    }

    override fun hashCode(): Int {
        var result = total.hashCode()
        result = 31 * result + remain.hashCode()
        return result
    }
}

data class PomodoroStateInfo(
    val state: PomodoroState,
    val progress: Float = state.remain / state.total.toFloat(),
    val remainLiteral: String = state.remain.run { "${div(60)}:${mod(60)}" }
)
