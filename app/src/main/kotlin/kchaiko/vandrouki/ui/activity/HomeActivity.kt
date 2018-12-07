package kchaiko.vandrouki.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kchaiko.vandrouki.navigation.Screens
import kchaiko.vandrouki.navigation.VandAppNavigator
import kchaiko.vandrouki.navigation.VandAppRouter
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.ui.component.activity.HomeActivityUI
import org.jetbrains.anko.setContentView
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder

class HomeActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    private val navigatorHolder by inject<NavigatorHolder>()
    val containerId: Int = View.generateViewId()
    private val navigator by lazy { VandAppNavigator(this, containerId) }
    val router by inject<VandAppRouter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeActivityUI().setContentView(this)
        router.newRootScreen(Screens.DiscountList)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun proceedError(exception: VandException) {
        AlertDialog.Builder(this)
                .setMessage(exception.message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    finish()
                }
                .create().show()
    }

}
