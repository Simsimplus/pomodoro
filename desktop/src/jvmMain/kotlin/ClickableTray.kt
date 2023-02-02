import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.util.*

private val iconSize: Size
    get() {
        val name = System.getProperty("os.name")
        return when {
            name?.startsWith("Linux") == true -> Size(22f, 22f)
            name?.startsWith("Win") == true -> Size(16f, 16f)
            name == "Mac OS X" -> Size(22f, 22f)
            else -> Size(32f, 32f)
        }
    }


@Suppress("unused")
@Composable
fun ApplicationScope.ClickableTray(
    icon: Painter,
    state: TrayState = rememberTrayState(),
    tooltip: String? = null,
    onAction: () -> Unit = {},
    onClicked: () -> Unit = {},
    menu: @Composable MenuScope.() -> Unit = {}
) {
    if (!isTraySupported) {
        DisposableEffect(Unit) {
            // We should notify developer, but shouldn't throw an exception.
            // If we throw an exception, some application wouldn't work on some platforms at
            // all, if developer doesn't check that application crashes.
            //
            // We can do this because we don't return anything in Tray function, and following
            // code doesn't depend on something that is created/calculated in this function.
            System.err.println(
                "Tray is not supported on the current platform. " +
                        "Use the global property `isTraySupported` to check."
            )
            onDispose {}
        }
        return
    }

    val currentOnAction by rememberUpdatedState(onAction)
    val currentOnClicked by rememberUpdatedState(onClicked)

    val awtIcon = remember(icon) {
        // We shouldn't use LocalDensity here because Tray's density doesn't equal it. It
        // equals to the density of the screen on which it shows. Currently Swing doesn't
        // provide us such information, it only requests an image with the desired width/height
        // (see MultiResolutionImage.getResolutionVariant). Resources like svg/xml should look okay
        // because they don't use absolute '.dp' values to draw, they use values which are
        // relative to their viewport.
        icon.toAwtImage(GlobalDensity, GlobalLayoutDirection, iconSize)
    }

    val tray = remember {
        TrayIcon(awtIcon).apply {
            isImageAutoSize = true
            addMouseListener(
                object : MouseListener {
                    override fun mouseClicked(e: MouseEvent?) {
                        println("mouseClicked")
                        currentOnClicked()
                    }

                    override fun mousePressed(e: MouseEvent?) {
                        println("mousePressed")
                    }

                    override fun mouseReleased(e: MouseEvent?) {
                        println("mouseReleased")
                    }

                    override fun mouseEntered(e: MouseEvent?) {
                        println("mouseEntered")
                    }

                    override fun mouseExited(e: MouseEvent?) {
                        println("mouseExited")
                    }

                }
            )
            addActionListener {
                currentOnAction()
            }
        }
    }
    val popupMenu = remember { PopupMenu() }
    val currentMenu by rememberUpdatedState(menu)

    SideEffect {
        if (tray.image != awtIcon) tray.image = awtIcon
        if (tray.toolTip != tooltip) tray.toolTip = tooltip
    }

    val composition = rememberCompositionContext()
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        tray.popupMenu = popupMenu

        val menuComposition = popupMenu.setContent(composition) {
            currentMenu()
        }

        SystemTray.getSystemTray().add(tray)

        state.notificationFlow
            .onEach(tray::displayMessage)
            .launchIn(coroutineScope)

        onDispose {
            menuComposition.dispose()
            SystemTray.getSystemTray().remove(tray)
        }
    }
}

private fun TrayIcon.displayMessage(notification: Notification) {
    val messageType = when (notification.type) {
        Notification.Type.None -> TrayIcon.MessageType.NONE
        Notification.Type.Info -> TrayIcon.MessageType.INFO
        Notification.Type.Warning -> TrayIcon.MessageType.WARNING
        Notification.Type.Error -> TrayIcon.MessageType.ERROR
    }

    displayMessage(notification.title, notification.message, messageType)
}

internal val GlobalDensity
    get() = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .defaultScreenDevice
        .defaultConfiguration
        .density

internal val Component.density: Density get() = graphicsConfiguration.density

private val GraphicsConfiguration.density: Density
    get() = Density(
        defaultTransform.scaleX.toFloat(),
        fontScale = 1f
    )

internal val GlobalLayoutDirection get() = Locale.getDefault().layoutDirection

internal val Component.layoutDirection: LayoutDirection
    get() = this.locale.layoutDirection

internal val Locale.layoutDirection: LayoutDirection
    get() = if (ComponentOrientation.getOrientation(this).isLeftToRight) {
        LayoutDirection.Ltr
    } else {
        LayoutDirection.Rtl
    }