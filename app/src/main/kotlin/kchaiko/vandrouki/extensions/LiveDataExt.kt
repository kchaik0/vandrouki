package kchaiko.vandrouki.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <D> LiveData<D>.observe(lifecycleOwner: LifecycleOwner, observeFun: D.() -> Unit) {
    observe(lifecycleOwner, Observer {
        it?.observeFun()
    })
}