package kchaiko.vandrouki.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import kchaiko.vandrouki.network.exception.BaseException

/**
 * Base activity for all programm activity
 *
 * Created by kchaiko on 08.10.2017.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun proceedError(exception: BaseException) {
        AlertDialog.Builder(this)
                .setMessage(exception.message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, { dialogInterface, _ -> dialogInterface.dismiss() })
                .create().show()
    }

}
