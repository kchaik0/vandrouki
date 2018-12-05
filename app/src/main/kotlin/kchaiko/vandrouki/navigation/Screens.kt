package kchaiko.vandrouki.navigation

import kchaiko.vandrouki.ui.fragment.DiscountFragment
import kchaiko.vandrouki.ui.fragment.DiscountListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object DiscountList : SupportAppScreen() {
        override fun getFragment() = DiscountListFragment.newInstance()
    }

    data class Discount(private val discount: kchaiko.vandrouki.beans.Discount) : SupportAppScreen() {
        override fun getFragment() = DiscountFragment.newInstance(discount)
    }

}