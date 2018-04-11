package kchaiko.vandrouki.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kchaiko.vandrouki.network.exception.BaseException

/**
 * Base activity for all programm activity
 *
 * Created by kchaiko on 08.10.2017.
 */

abstract class BaseActivity : AppCompatActivity() {

    fun proceedError(exception: BaseException) {
        AlertDialog.Builder(this)
                .setMessage(exception.message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, { dialogInterface, _ -> dialogInterface.dismiss() })
                .create().show()
    }

}
