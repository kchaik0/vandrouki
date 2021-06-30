package kchaiko.vandrouki.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.google.accompanist.coil.rememberCoilPainter
import kchaiko.vandrouki.R

@Composable
fun String.toCoilPainter(): Painter = rememberCoilPainter(
    request = this,
    previewPlaceholder = R.drawable.placeholder_img_discount
)