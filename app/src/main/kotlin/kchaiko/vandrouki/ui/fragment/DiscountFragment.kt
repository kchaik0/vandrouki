package kchaiko.vandrouki.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.createView
import kchaiko.vandrouki.ui.component.fragment.DiscountUI
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DiscountFragment : BaseFragment() {

    companion object {
        private const val DISCOUNT_ARG = "discount"
        fun newInstance(discount: Discount) = DiscountFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DISCOUNT_ARG, discount)
            }
        }
    }

    private val viewModel: DiscountViewModel by viewModel()
    private lateinit var discount: Discount

    lateinit var tvFullText: TextView
    lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        discount = arguments?.getParcelable(DISCOUNT_ARG)!!
        with(viewModel) {
            loadingDelegate { showLoadingIndicator(it) }
            dataDelegate { populateData(it) }
            errorDelegate { proceedError(it) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            DiscountUI(discount, viewModel.getFavourite(discount.detailUrlPart)) {
                viewModel.saveData(discount, it)
            }.createView(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.provideData(discount.detailUrlPart)
    }

    private fun showLoadingIndicator(showLoading: Boolean) {
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