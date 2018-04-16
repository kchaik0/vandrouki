package kchaiko.vandrouki.viewmodel

import android.databinding.ObservableField
import io.reactivex.rxkotlin.subscribeBy
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.enumes.request.RequestStatus.*
import kchaiko.vandrouki.network.exception.BaseException
import kchaiko.vandrouki.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(private val discountRepository: DiscountRepository) : BaseViewModel() {

    lateinit var itemClick: (Discount) -> Unit
    lateinit var errorDelegate: (BaseException) -> Unit

    var isLoading: ObservableField<Boolean> = ObservableField()
    var adapter: ObservableField<DiscountAdapter> = ObservableField()

    fun subscribe() {
        compositeDisposable.add(discountRepository.discountListSubject.subscribeBy {
            isLoading.set(it.status == LOADING)
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { adapter.set(DiscountAdapter(it, itemClick)) }
                    isLoading.set(false)
                }
                ERROR -> {
                    isLoading.set(false)
                    it?.exception?.apply {
                        errorDelegate(this)
                    }
                }
                LOADING -> isLoading.set(true)
            }
        })
    }

    fun itemClick(function: (Discount) -> Unit) {
        this.itemClick = function
    }

    fun errorDelegate(function: (BaseException) -> Unit) {
        this.errorDelegate = function
    }

}