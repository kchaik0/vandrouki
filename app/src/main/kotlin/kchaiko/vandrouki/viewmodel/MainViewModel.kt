package kchaiko.vandrouki.viewmodel

import android.databinding.ObservableField
import io.reactivex.rxkotlin.subscribeBy
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.enumes.request.RequestStatus.*
import kchaiko.vandrouki.network.exception.BaseException
import kchaiko.vandrouki.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(private val discountRepository: DiscountRepository) : BaseViewModel() {

    private lateinit var dataDelegate: (List<Discount>) -> Unit
    private lateinit var errorDelegate: (BaseException) -> Unit

    var isLoading: ObservableField<Boolean> = ObservableField()

    fun subscribe() {
        compositeDisposable.add(discountRepository.discountListSubject.subscribeBy { provideResult(it) })
    }

    fun dataDelegate(function: (List<Discount>) -> Unit) {
        this.dataDelegate = function
    }

    fun errorDelegate(function: (BaseException) -> Unit) {
        this.errorDelegate = function
    }

    private fun provideResult(it: Resource<List<Discount>>) {
        when (it.status) {
            SUCCESS -> {
                dataDelegate(it.data ?: listOf())
                isLoading.set(false)
            }
            ERROR -> {
                isLoading.set(false)
                it.exception?.apply {
                    errorDelegate(this)
                }
            }
            LOADING -> isLoading.set(true)
        }
    }

}