package kchaiko.vandrouki.ui.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount

class ComposeDiscountFragment : Fragment() {

    companion object {
        const val DISCOUNT_ARG = "discount"
    }

    private val discount: Discount by lazy { arguments?.getParcelable(DISCOUNT_ARG)!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DiscountView(imageUrl = discount.image)
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DiscountViewPreview() {
    DiscountView()
}

@Composable
fun DiscountView(imageUrl: String = "") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = getGlidePainter(imageUrl = imageUrl),
            contentDescription = stringResource(id = R.string.discount_image_content_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
    }
}