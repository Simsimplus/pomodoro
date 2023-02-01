package io.simsim.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import java.time.Duration

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Float(
    modifier: Modifier = Modifier,
    onFinish: () -> Unit
) {
    var dragOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var dragDpOffset = with(LocalDensity.current) {
        DpOffset(dragOffset.x.toDp(), dragOffset.y.toDp())
    }
    val totalSeconds = Duration.ofMinutes(5).seconds
    val appState = rememberAppState(totalSeconds)
    Theme {
        PomodoroFloat(
            modifier = modifier.onDrag(onDragStart = {
                dragOffset = it
            }, onDrag = {
                dragOffset += it
            }),
            appState = appState,
        )


    }
}