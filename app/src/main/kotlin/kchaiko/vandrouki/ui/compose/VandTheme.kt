package kchaiko.vandrouki.ui.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun VandTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        colors = DefaultColors
    )
}

private val DefaultColors = lightColors(
    surface = Color.Red
)