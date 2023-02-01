package io.simsim.common

actual fun getPlatformName(): String {
    return "Desktop"
}

actual val isDesktop = true