package kchaiko.vandrouki.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.functions.Consumer
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.beans.DiscountBeanList
import kchaiko.vandrouki.network.LoadUrlManager

/**
 * Activity for show discount details
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount)
    }

}
