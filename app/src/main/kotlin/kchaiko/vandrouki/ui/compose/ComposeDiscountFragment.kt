package kchaiko.vandrouki.ui.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.htmlText
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ComposeDiscountFragment : Fragment() {

    companion object {
        const val DISCOUNT_ARG = "discount"
    }

    private val discount: Discount by lazy { arguments?.getParcelable(DISCOUNT_ARG)!! }
    private val viewModel: DiscountViewModel by viewModel { parametersOf(discount.detailUrlPart) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DiscountViewScreen(discount = discount, viewModel = viewModel)
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DiscountViewPreview() {
    DiscountView(discount = mockDiscount, fullDesc = mockFullDesc)
}

@Composable
fun DiscountViewScreen(discount: Discount, viewModel: DiscountViewModel) {
    val detailedDiscountState: State<DetailedDiscount?> = viewModel.modelLiveData.observeAsState()
    DiscountView(discount = discount, detailedDiscountState.value?.fullDesc ?: "")
}

@Composable
fun DiscountView(discount: Discount, fullDesc: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = getCoilPainter(discount.image),
            contentDescription = stringResource(id = R.string.discount_image_content_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = discount.getDateFormatted(),
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = discount.author,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = discount.title,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = fullDesc.htmlText,
                style = MaterialTheme.typography.body2
            )
        }
    }
}