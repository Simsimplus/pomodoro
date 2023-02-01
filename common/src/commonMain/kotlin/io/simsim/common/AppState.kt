package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class AppState(val totalSeconds: Long, private val tickRate: Long = 1) {
    private val client by lazy {
        HttpClient(CIO)
    }
    val clockState
        @Composable get() = rememberClockState(totalSeconds, tickRate)

    val shouldHandleHoverEvent = isDesktop

    suspend fun getOneWord() = suspend {
        val response = client.get("https://api.uixsj.cn/hitokoto/get")
        response.bodyAsText()
    }.invoke()
}

@Composable
fun rememberAppState(totalSeconds: Long, tickRate: Long = 1) = remember(totalSeconds, tickRate) {
    AppState(totalSeconds, tickRate)
}