package kchaiko.vandrouki.ui.fragment

import android.support.v4.app.Fragment
import kchaiko.vandrouki.navigation.VandAppRouter
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.ui.activity.HomeActivity

abstract class BaseFragment : Fragment() {

    fun proceedError(exception: VandException) {
        baseActivity.proceedError(exception)
    }

    protected val router: VandAppRouter
        get() = baseActivity.router

    private val baseActivity: HomeActivity
        get() =
            if (activity is HomeActivity) activity as HomeActivity
            else throw VandException("All fragment must be in BaseActivity")

}