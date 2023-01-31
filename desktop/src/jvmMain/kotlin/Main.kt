import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import io.simsim.common.App


fun main() = application {
    val windowState = WindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(Alignment.BottomEnd),
        height = 123.6.dp,
        width = 200.dp
    )
    Window(
        state = windowState,
        title = "Pomodoro",
        icon = painterResource("ic/ic.png"),
        onCloseRequest = ::exitApplication,
        undecorated = true
    ) {
        App()
    }
}
