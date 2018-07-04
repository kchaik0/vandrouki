package kchaiko.vandrouki.viewmodel

import android.databinding.ObservableField
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.enumes.request.RequestStatus.*
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.repository.DiscountRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(private val discountRepository: DiscountRepository) : BaseViewModel() {

    private lateinit var dataDelegate: (List<Discount>) -> Unit
    private lateinit var errorDelegate: (VandException) -> Unit

    var isLoading: ObservableField<Boolean> = ObservableField()

    fun provideData() = launch(UI) {
        provideLoading(true)
        val resource = discountRepository.getDataResource()
        provideResult(resource)
    }

    fun dataDelegate(function: (List<Discount>) -> Unit) = apply { dataDelegate = function }

    fun errorDelegate(function: (VandException) -> Unit) = apply { errorDelegate = function }

    private fun provideLoading(loading: Boolean = false) {
        isLoading.set(loading)
    }

    private fun provideResult(it: Resource<DiscountList>) {
        when (it.status) {
            SUCCESS -> {
                it.data?.apply { dataDelegate(this) }
                provideLoading()
            }
            ERROR -> {
                provideLoading()
                it.exception?.apply { errorDelegate(this) }
            }
            LOADING -> provideLoading(true)
        }
    }

}