package kchaiko.vandrouki.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.google.accompanist.glide.rememberGlidePainter
import kchaiko.vandrouki.R

@Composable
fun getGlidePainter(imageUrl: String): Painter = rememberGlidePainter(
    request = imageUrl,
    previewPlaceholder = R.drawable.placeholder_img_discount
)