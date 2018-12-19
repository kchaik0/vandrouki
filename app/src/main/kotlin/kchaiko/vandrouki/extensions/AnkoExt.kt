package kchaiko.vandrouki.extensions

import android.view.ViewGroup
import android.view.ViewManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView

const val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
const val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT

fun <T : Fragment> AnkoComponent<T>.createView(fragment: T) = createView(AnkoContext.create(fragment.requireActivity(), fragment))

inline fun ViewManager.recyclerViewX(init: RecyclerView.() -> Unit) = ankoView(factory = { RecyclerView(it) }, theme = 0, init = init)