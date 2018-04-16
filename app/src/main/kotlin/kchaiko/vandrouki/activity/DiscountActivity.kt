package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_discount.*

/**
 * Activity for show discount details
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountActivity : BaseActivity() {

    companion object {

        private val DISCOUNT_BEAN_EXTRA = "discount_bean"

        fun getIntent(context: Context, discount: Discount): Intent {
            val intent = Intent(context, DiscountActivity::class.java)
            intent.putExtra(DISCOUNT_BEAN_EXTRA, discount)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount)
        val discountBean = intent.extras.getParcelable<Discount>(DISCOUNT_BEAN_EXTRA)
        ad_title.text = discountBean.title
    }

    override fun getViewModel(): BaseViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
