package io.simsim.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.time.Duration

class AppState(private val totalDuration: Duration, private val tickRate: Long = 1) {
    private val client by lazy {
        HttpClient(CIO)
    }
    val clockState
        @Composable get() = rememberClockState(totalDuration, tickRate)

    suspend fun getOneWord() = suspend {
        val response = client.get("https://api.uixsj.cn/hitokoto/get")
        response.bodyAsText()
    }.invoke()
}

@Composable
fun rememberAppState(totalDuration: Duration, tickRate: Long = 1) = remember(totalDuration, tickRate) {
    AppState(totalDuration, tickRate)
}