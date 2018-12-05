package kchaiko.vandrouki.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import kotlinx.android.synthetic.main.fragment_discount.*
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
            inflater.inflate(R.layout.fragment_discount, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        organizeLayout()
        viewModel.provideData(discount.detailUrlPart)
    }

    private fun organizeLayout() {
        with(discount) {
            Picasso.with(activity).load(image).into(fd_image)
            fd_date.text = getDateFormatted()
            fd_favourite.setOnClickListener {
                ClickListener(viewModel).onClick(it)
            }
            fd_favourite.isChecked = viewModel.getFavourite(detailUrlPart)
            fd_author.text = author
            fd_title.text = title
        }
        fd_full_desc.movementMethod = LinkMovementMethod()
    }

    private fun showLoadingIndicator(showLoading: Boolean) {
        fd_full_desc.visibility = if (showLoading) View.GONE else View.VISIBLE
        fd_loading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun populateData(detailedDiscount: DetailedDiscount) {
        with(detailedDiscount.fullDesc) {
            @Suppress("DEPRECATION")
            fd_full_desc.text = if (this.isNotEmpty())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
                else
                    Html.fromHtml(this)
            else ""
        }
    }

    inner class ClickListener(private val viewModel: DiscountViewModel) {
        fun onClick(view: View) {
            viewModel.saveData(discount, (view as CheckBox).isChecked)
        }
    }

}