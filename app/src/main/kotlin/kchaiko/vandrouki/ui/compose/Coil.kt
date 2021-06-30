package kchaiko.vandrouki.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.google.accompanist.coil.rememberCoilPainter
import kchaiko.vandrouki.R

@Composable
fun getCoilPainter(imageUrl: String): Painter = rememberCoilPainter(
    request = imageUrl,
    previewPlaceholder = R.drawable.placeholder_img_discount
)