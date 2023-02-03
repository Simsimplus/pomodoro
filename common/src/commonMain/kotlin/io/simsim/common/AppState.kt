package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.simsim.common.storage.SettingHelper

class AppState(onStateChange: (PomodoroStateInfo) -> Unit = {}) {
    //    private val client by lazy {
//        HttpClient(CIO)
//    }
    private val autoStart = SettingHelper.getSetting("autoStart")
    val clockState = ClockState(autoStart, onStateChange)

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