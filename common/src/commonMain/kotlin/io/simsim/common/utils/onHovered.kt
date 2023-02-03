package io.simsim.common.utils

import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput


fun Modifier.onHovered(
    onHovered: () -> Unit
): Modifier = composed {
    val currentOnHovered by rememberUpdatedState(onHovered)
    pointerInput(true) {
        forEachGesture {
            awaitPointerEventScope {
                val event = awaitPointerEvent()
                if (event.type == PointerEventType.Enter) {
                    currentOnHovered()
                }
            }
        }
    }
}
