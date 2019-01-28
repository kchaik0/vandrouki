package kchaiko.vandrouki.ui.component.fragment

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import kchaiko.vandrouki.R
import kchaiko.vandrouki.extensions.MATCH_PARENT
import kchaiko.vandrouki.extensions.WRAP_CONTENT
import kchaiko.vandrouki.extensions.getThemeAttrColor
import kchaiko.vandrouki.extensions.recyclerViewX
import kchaiko.vandrouki.ui.fragment.DiscountListFragment
import org.jetbrains.anko.*

class DiscountListUI : AnkoComponent<DiscountListFragment> {

    override fun createView(ui: AnkoContext<DiscountListFragment>): View = with(ui) {
        frameLayout {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            backgroundColor = ui.ctx.getThemeAttrColor(R.attr.colorDefault)
            recyclerViewX {
                ui.owner.rvDiscountList = this
                clipToPadding = true
            }.lparams(width = MATCH_PARENT, height = MATCH_PARENT)
            progressBar {
                ui.owner.pbLoading = this
                visibility = View.GONE
            }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT, gravity = Gravity.CENTER)
        }
    }

}