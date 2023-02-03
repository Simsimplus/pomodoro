package io.simsim.android

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern
import com.lzf.easyfloat.interfaces.OnPermissionResult
import com.lzf.easyfloat.permission.PermissionUtils
import io.simsim.common.App
import io.simsim.common.AppState
import io.simsim.common.defaultFocusColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private val appState = AppState()

    private val floatingView by lazy {
        LinearProgressIndicator(this).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2Px(2f).roundToInt())
            this.trackColor = defaultFocusColor.toArgb()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Settings.canDrawOverlays(this)) requestOverlayPermission(
            object : OnPermissionResult {
                override fun permissionResult(isOpen: Boolean) {
                    showFloatView()
                }
            }
        ) else {
            showFloatView()
        }
        setContent {
            MaterialTheme {
                val appState = remember {
                    appState
                }
                App(appState)
            }
        }
        lifecycleScope.launch {
            appState.clockState.pomodoroStateInfoFlow.collectLatest {
                floatingView.setProgress((it.progress * 100).roundToInt(), true)
            }
        }
    }

    fun showFloatView() {
        EasyFloat.with(this)
            .setLayout(floatingView)
            .setGravity(Gravity.RIGHT)
            .setMatchParent(widthMatch = true, heightMatch = false)
            .setDragEnable(false)
            .setSidePattern(SidePattern.DEFAULT)
            .setShowPattern(ShowPattern.ALL_TIME)
            .setTag("float")
            .setImmersionStatusBar(true)
            .show()
    }

    private fun dp2Px(dp: Float) = dp * resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT.toFloat()

    private fun Activity.requestOverlayPermission(onPermissionResult: OnPermissionResult) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            startActivity(
                Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                ).apply {
                    data = "package:$packageName".toUri()
                }
            )
        } else PermissionUtils.requestPermission(this, onPermissionResult)
    }

}