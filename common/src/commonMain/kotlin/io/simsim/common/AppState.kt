package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class AppState(onStateChange: (PomodoroStateInfo) -> Unit = {}) {
    //    private val client by lazy {
//        HttpClient(CIO)
//    }
    val clockState = ClockState(onStateChange)

    val shouldHandleHoverEvent = isDesktop

//    suspend fun getOneWord() = suspend {
//        val response = client.get("https://api.uixsj.cn/hitokoto/get")
//        response.bodyAsText()
//    }.invoke()
}

@Composable
fun rememberAppState(onStateChange: (PomodoroStateInfo) -> Unit = {}) = remember(onStateChange) {
    AppState(onStateChange)
}