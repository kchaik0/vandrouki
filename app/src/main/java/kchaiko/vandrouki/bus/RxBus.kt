package kchaiko.vandrouki.bus

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * Bus by rx
 *
 * Created by kchaiko on 06.07.2017.
 */
class RxBus {

    val bus: Relay<Any> = PublishRelay.create()

    fun send(event: Any) {
        if (bus.hasObservers()) {
            bus.accept(event)
        }
    }

    fun asFlowable(): Flowable<Any> {
        return bus.toFlowable(BackpressureStrategy.LATEST)
    }

}