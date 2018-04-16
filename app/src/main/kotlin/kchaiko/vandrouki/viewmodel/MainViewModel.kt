package kchaiko.vandrouki.viewmodel

import android.databinding.ObservableField
import io.reactivex.rxkotlin.subscribeBy
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(private val discountRepository: DiscountRepository) : BaseViewModel() {

    lateinit var itemClick: (Discount) -> Unit

    fun subscribe() {
        compositeDisposable.add(discountRepository.discountListSubject.subscribeBy {
            isLoading.set(it.status == RequestStatus.LOADING)
            if (it.status == RequestStatus.SUCCESS) {
                it.data?.let { adapter.set(DiscountAdapter(it, itemClick)) }
            }
        })
    }

    var isLoading: ObservableField<Boolean> = ObservableField()

    var adapter: ObservableField<DiscountAdapter> = ObservableField()

}