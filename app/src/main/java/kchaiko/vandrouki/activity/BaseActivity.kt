package kchaiko.vandrouki.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity

/**
 * Base activity for all programm activity
 *
 * Created by kchaiko on 08.10.2017.
 */

abstract class BaseActivity : AppCompatActivity() {

    fun proceedError(error: String?) {
        AlertDialog.Builder(this)
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, { dialogInterface, i -> dialogInterface.dismiss() })
                .create().show()
    }

}
