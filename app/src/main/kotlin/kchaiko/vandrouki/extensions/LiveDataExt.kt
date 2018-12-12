package kchaiko.vandrouki.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <D> LiveData<D>.observe(lifecycleOwner: LifecycleOwner, observeFun: D.() -> Unit) {
    observe(lifecycleOwner, Observer {
        it?.observeFun()
    })
}