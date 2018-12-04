package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.CheckBox
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import kotlinx.android.synthetic.main.activity_discount.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Activity for show discount details
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, DiscountActivity::class.java)
    }

    private val viewModel: DiscountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount)
        organizeLayout()
        with(viewModel) {
            loadingDelegate { showLoadingIndicator(it) }
            dataDelegate { populateData(it) }
            errorDelegate { proceedError(it) }
            provideData()
        }
    }

    private fun showLoadingIndicator(showLoading: Boolean) {
        ad_full_desc.visibility = if (showLoading) View.GONE else View.VISIBLE
        ad_loading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun organizeLayout() {
        with(viewModel.discount) {
            Picasso.with(this@DiscountActivity).load(image).into(ad_image)
            ad_date.text = getDateFormatted()
            ad_favourite.setOnClickListener {
                ClickListener(viewModel).onClick(it)
            }
            ad_favourite.isChecked = viewModel.getFavourite()
            ad_author.text = author
            ad_title.text = title
        }
        ad_full_desc.movementMethod = LinkMovementMethod()
    }

    private fun populateData(detailedDiscount: DetailedDiscount) {
        with(detailedDiscount.fullDesc) {
            ad_full_desc.text = if (this.isNotEmpty())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
                else
                    Html.fromHtml(this)
            else ""
        }
    }
}

class ClickListener(private val viewModel: DiscountViewModel) {
    fun onClick(view: View) {
        viewModel.saveData((view as CheckBox).isChecked)
    }
}
