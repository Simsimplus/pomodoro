package io.simsim.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xfffc3c04)
val defaultFocusColor = Color(0xfffc3c04)
val defaultBreakColor = Color(0xff49a94d)

data class PomodoroColors(
    val focusColor: Color = defaultFocusColor,
    val breakColor: Color = defaultBreakColor,
)

val LocalPomodoroColors = compositionLocalOf { PomodoroColors() }

@Composable
fun PomodoroTheme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalPomodoroColors provides PomodoroColors()) {
        MaterialTheme(
            colors = if (isSystemInDarkTheme) darkColors() else lightColors(
                primary = primaryColor,
                primaryVariant = Color(0xebb7b7)
            ),
            typography = Typography(),
            shapes = Shapes(),
            content = content
        )
    }

}