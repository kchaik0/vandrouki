package kchaiko.vandrouki.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import kchaiko.vandrouki.R
import kchaiko.vandrouki.ui.ViewIds
import kchaiko.vandrouki.ui.component.activity.HomeActivityUI
import org.jetbrains.anko.setContentView

class HomeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        HomeActivityUI().setContentView(this)
        if (savedInstanceState == null) {
            val mainHost = NavHostFragment.create(R.navigation.main_graph)
            supportFragmentManager.commit {
                replace(ViewIds.FRAGMENT_CONTAINER, mainHost)
                setPrimaryNavigationFragment(mainHost)
            }
        }
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
