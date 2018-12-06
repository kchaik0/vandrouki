package kchaiko.vandrouki.extensions

import android.support.v4.app.Fragment
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

fun <T : Fragment> AnkoComponent<T>.createView(fragment: T) = createView(AnkoContext.create(fragment.requireActivity(), fragment))

const val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT

const val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT