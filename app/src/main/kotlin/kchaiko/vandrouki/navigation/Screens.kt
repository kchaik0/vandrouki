package kchaiko.vandrouki.navigation

import androidx.fragment.app.Fragment
import kchaiko.vandrouki.navigation.androidx.AppScreen
import kchaiko.vandrouki.ui.fragment.DiscountFragment
import kchaiko.vandrouki.ui.fragment.DiscountListFragment

object Screens {

    object DiscountList : AppScreen() {
        override val fragment: Fragment? = DiscountListFragment.newInstance()
    }

    data class Discount(private val discount: kchaiko.vandrouki.beans.Discount) : AppScreen() {
        override val fragment: Fragment? = DiscountFragment.newInstance(discount)
    }

}