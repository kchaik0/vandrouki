package kchaiko.vandrouki.ui.component.activity

import android.view.View
import android.widget.FrameLayout
import kchaiko.vandrouki.extensions.MATCH_PARENT
import kchaiko.vandrouki.ui.ViewIds
import kchaiko.vandrouki.ui.activity.HomeActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout

class HomeActivityUI : AnkoComponent<HomeActivity> {

    override fun createView(ui: AnkoContext<HomeActivity>): View = with(ui) {
        frameLayout {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            fitsSystemWindows = true
            id = ViewIds.FRAGMENT_CONTAINER
        }
    }

}