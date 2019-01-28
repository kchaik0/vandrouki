package kchaiko.vandrouki.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import kchaiko.vandrouki.R
import kchaiko.vandrouki.navigation.Screens
import kchaiko.vandrouki.navigation.VandAppNavigator
import kchaiko.vandrouki.navigation.VandAppRouter
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.ui.component.activity.HomeActivityUI
import org.jetbrains.anko.setContentView
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder

class HomeActivity : FragmentActivity() {

    private val navigatorHolder by inject<NavigatorHolder>()
    val containerId: Int = View.generateViewId()
    private val navigator by lazy { VandAppNavigator(this, containerId) }
    val router by inject<VandAppRouter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        HomeActivityUI().setContentView(this)
        if (savedInstanceState == null) {
            router.newRootScreen(Screens.DiscountList)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun proceedError(errorMessage: String?) {
        AlertDialog.Builder(this)
                .setMessage(errorMessage)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    finish()
                }
                .create().show()
    }

}
