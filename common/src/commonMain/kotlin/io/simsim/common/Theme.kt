package io.simsim.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xfffc3c04)
val accentColor = Color(0xfffc3c04)
val colors = lightColors()

@Composable
fun Theme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme) darkColors() else lightColors(),
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}