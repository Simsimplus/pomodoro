import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import io.simsim.Setting
import io.simsim.common.App
import io.simsim.common.Float
import io.simsim.common.rememberAppState
import io.simsim.common.storage.SettingHelper


fun main() = application {
    val settings by SettingHelper.getAllSettings().collectAsState(emptyMap())
    val uiMode by remember {
        mutableStateOf(UiMode.Float)
    }
    val dpSize = when (uiMode) {
        UiMode.Full -> DpSize(200.dp, 123.6.dp)
        UiMode.Float -> DpSize(Dp.Unspecified, 2.5.dp)
    }
    val dialogState = rememberDialogState(
        position = WindowPosition.Aligned(Alignment.TopCenter),
        size = dpSize
    )
    val trayState = rememberTrayState()
    var isWindowVisible by rememberSaveable {
        mutableStateOf(true)
    }
    var trayTip by remember {
        mutableStateOf<String?>(null)
    }
    val appState = rememberAppState {
        trayTip = "\uD83D\uDFE5 ${it.remainLiteral}"
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
            UiMode.Full -> App(appState)
            UiMode.Float -> Float(Modifier.fillMaxSize(), appState)
        }
    }

    ClickableTray(
        icon = painterResource("ic/ic.png"),
        state = trayState,
        tooltip = trayTip,
        onAction = {
            isWindowVisible = !isWindowVisible
        },
        onClicked = {
            println("mouse button clicked $it")
            if (it == 1) {
                isWindowVisible = !isWindowVisible
            }
        },
        menu = {
            Menu("setting") {
                val autoStart by settings.withDefault { "" }
                CheckboxItem("autoStart", checked = autoStart == "true") { checked ->
                    SettingHelper.upsertSetting(
                        Setting("autoStart", checked.toString())
                    )
                    if (checked) {
                        appState.clockState.resumeTimer()
                    }
                }
            }
            Item(text = "hide") {
                isWindowVisible = false
            }
            Item(text = "pause") {
                appState.clockState.pauseTimer()
            }
            Item(text = "resume") {
                appState.clockState.resumeTimer()
            }
            Item(text = "reset") {
                appState.clockState.resetTimer()
            }
            Item(text = "exit") {
                this@application.exitApplication()
            }
        }
    )
}

enum class UiMode {
    Full, Float
}
