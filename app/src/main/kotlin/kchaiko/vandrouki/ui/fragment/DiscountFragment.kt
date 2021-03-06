package kchaiko.vandrouki.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.db.entity.FavouriteDiscount
import kchaiko.vandrouki.extensions.createView
import kchaiko.vandrouki.extensions.observe
import kchaiko.vandrouki.ui.component.fragment.DiscountUI
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DiscountFragment : BaseFragment() {

    companion object {
        const val DISCOUNT_ARG = "discount"
    }

    override val viewModel: DiscountViewModel by viewModel { parametersOf(discount.detailUrlPart) }
    private val discount: Discount by lazy { arguments?.getParcelable<Discount>(DISCOUNT_ARG)!! }
    private lateinit var favouriteDiscount: FavouriteDiscount

    lateinit var tvFullText: TextView
    lateinit var pbLoading: ProgressBar
    lateinit var cbFavourite: CheckBox

    override fun bindLiveData() {
        super.bindLiveData()
        viewModel.modelLiveData.observe(viewLifecycleOwner) {
            populateData(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            DiscountUI(discount) {
                if (::favouriteDiscount.isInitialized) {
                    favouriteDiscount.isFavourite = it
                    viewModel.updateData(favouriteDiscount)
                } else {
                    viewModel.saveData(discount, it)
                }
            }.createView(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getFavourite(discount.detailUrlPart).observe(viewLifecycleOwner) {
            favouriteDiscount = this
            cbFavourite.isChecked = isFavourite
        }
    }

    override fun showLoadingIndicator(showLoading: Boolean) {
        tvFullText.visibility = if (showLoading) View.GONE else View.VISIBLE
        pbLoading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun populateData(detailedDiscount: DetailedDiscount) {
        with(detailedDiscount.fullDesc) {
            @Suppress("DEPRECATION")
            tvFullText.text = if (this.isNotEmpty())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
                else
                    Html.fromHtml(this)
            else ""
        }
    }

}