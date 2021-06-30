package kchaiko.vandrouki.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.db.entity.FavouriteDiscount
import kchaiko.vandrouki.extensions.htmlText
import kchaiko.vandrouki.extensions.toCoilPainter
import kchaiko.vandrouki.ui.sample.FakeFullDiscountProvider
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
fun DiscountViewPreview_isFavorite(@PreviewParameter(FakeFullDiscountProvider::class) fakeFullDiscount: Pair<Discount, String>) {
    DiscountView(
        discount = fakeFullDiscount.first,
        fullDesc = fakeFullDiscount.second,
        isFavorite = true,
        favoriteClick = {})
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DiscountViewPreview_isNotFavorite(@PreviewParameter(FakeFullDiscountProvider::class) fakeFullDiscount: Pair<Discount, String>) {
    DiscountView(
        discount = fakeFullDiscount.first,
        fullDesc = fakeFullDiscount.second,
        isFavorite = false,
        favoriteClick = {})
}

@Composable
fun DiscountViewScreen(discount: Discount, viewModel: DiscountViewModel) {
    val detailedDiscountState: State<DetailedDiscount?> = viewModel.modelLiveData.observeAsState()
    val favoriteState: State<FavouriteDiscount?> =
        viewModel.getFavourite(discount.detailUrlPart).observeAsState()
    Log.d(
        "DiscountViewScreen",
        "DetailedDiscount: ${
            detailedDiscountState.value?.fullDesc?.substring(
                0,
                20
            )
        }, favorite: ${favoriteState.value}"
    )
    DiscountView(
        discount = discount,
        fullDesc = detailedDiscountState.value?.fullDesc ?: "",
        isFavorite = favoriteState.value?.isFavourite ?: false,
        favoriteClick = { isFavorite ->
            viewModel.updateFavorite(
                favoriteDiscount = favoriteState.value,
                discountModel = discount,
                isFavorite = isFavorite
            )
        }
    )
}

@Composable
fun DiscountView(
    discount: Discount,
    fullDesc: String,
    isFavorite: Boolean,
    favoriteClick: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = discount.image.toCoilPainter(),
            contentDescription = stringResource(id = R.string.discount_image_content_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row {
                Text(
                    text = discount.getDateFormatted(),
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.weight(weight = 1f))
                Checkbox(checked = isFavorite, onCheckedChange = favoriteClick)
            }
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