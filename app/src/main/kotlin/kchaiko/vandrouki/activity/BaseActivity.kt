package kchaiko.vandrouki.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import kchaiko.vandrouki.annotation.ViewModel
import kchaiko.vandrouki.network.exception.BaseException
import kchaiko.vandrouki.viewmodel.BaseViewModel

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

    override fun onDestroy() {
        super.onDestroy()
        clearViewModels()
    }

    protected fun proceedError(exception: BaseException) {
        AlertDialog.Builder(this)
                .setMessage(exception.message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, { dialogInterface, _ -> dialogInterface.dismiss() })
                .create().show()
    }

    private fun clearViewModels() {
        javaClass.declaredFields.filter { it.getAnnotation(ViewModel::class.java) != null }
                .map { it.get(this) as BaseViewModel }
                .forEach { it.clearViewModel() }
    }

}
