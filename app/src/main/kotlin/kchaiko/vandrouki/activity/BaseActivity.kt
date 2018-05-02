package kchaiko.vandrouki.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import kchaiko.vandrouki.network.exception.BaseException
import kchaiko.vandrouki.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Base activity for all programm activity
 *
 * Created by kchaiko on 08.10.2017.
 */

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        viewModel.attach()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.detach()
    }

    protected fun proceedError(exception: BaseException) {
        AlertDialog.Builder(this)
                .setMessage(exception.message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, { dialogInterface, _ -> dialogInterface.dismiss() })
                .create().show()
    }

}
