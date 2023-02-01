import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import io.simsim.common.App
import io.simsim.common.Float


fun main() = application {
    val uiMode by remember {
        mutableStateOf(UiMode.Float)
    }
    val dpSize = when (uiMode) {
        UiMode.Full -> DpSize(200.dp, 123.6.dp)
        UiMode.Float -> DpSize(Dp.Unspecified, 5.dp)
    }
    val dialogState = rememberDialogState(
        position = WindowPosition.Aligned(Alignment.BottomEnd),
        size = dpSize
    )
    val trayState = rememberTrayState()
    var isWindowVisible by rememberSaveable {
        mutableStateOf(true)
    }
    Dialog(
        visible = isWindowVisible,
        state = dialogState,
        title = "Pomodoro",
        icon = painterResource("ic/ic.png"),
        onCloseRequest = {
            isWindowVisible = false
        },
        undecorated = true,
        transparent = true,
        resizable = false,
        focusable = false,
    ) {
        window.isAlwaysOnTop = true
        when (uiMode) {
            UiMode.Full -> App()
            UiMode.Float -> Float(Modifier.fillMaxSize()) {
            }
        }
    }
    Tray(
        icon = painterResource("ic/ic.png"),
        state = trayState,
        tooltip = "Pomodoro",
        onAction = {
            isWindowVisible = !isWindowVisible
        },
        menu = {
            Item(text = "hide") {
                isWindowVisible = false
            }
        }
    )
}

enum class UiMode {
    Full, Float
}
