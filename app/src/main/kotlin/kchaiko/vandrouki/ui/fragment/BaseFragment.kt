package kchaiko.vandrouki.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import kchaiko.vandrouki.extensions.observe
import kchaiko.vandrouki.navigation.VandAppRouter
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.ui.activity.HomeActivity
import kchaiko.vandrouki.viewmodel.BaseViewModel

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel<*>

    private fun proceedError(exception: VandException) {
        baseActivity.proceedError(exception.message)
    }

    protected val router: VandAppRouter
        get() = baseActivity.router

    abstract fun showLoadingIndicator(showLoading: Boolean)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindLiveData()
    }

    open fun bindLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            showLoadingIndicator(this)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            proceedError(this)
        }
    }

    private val baseActivity: HomeActivity
        get() =
            if (activity is HomeActivity) activity as HomeActivity
            else throw VandException("All fragment must be in HomeActivity")

}