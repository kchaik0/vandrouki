package kchaiko.vandrouki.ui.recycler.holder

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import kchaiko.vandrouki.R
import kchaiko.vandrouki.extensions.MATCH_PARENT
import kchaiko.vandrouki.extensions.WRAP_CONTENT
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kchaiko.vandrouki.ui.recycler.item.BaseItem
import kchaiko.vandrouki.ui.recycler.item.NavigationItem
import org.jetbrains.anko.*

class NavigationItemViewHolder(context: Context, component: NavigationItemUI) : BaseViewHolder(component.createView(AnkoContext.create(context))) {

    private val prevBtn: View = component.prevBtn
    private val nextBtn: View = component.nextBtn

    override fun bindData(data: BaseItem) {
        if (data is NavigationItem) {
            prevBtn.setOnClickListener {
                data.plusPage()
            }
            nextBtn.visibility = if (data.page > DEFAULT_PAGE) View.VISIBLE else View.GONE
            nextBtn.setOnClickListener {
                data.minusPage()
            }
        }
    }
}

class NavigationItemUI : AnkoComponent<Context> {

    lateinit var prevBtn: View
    lateinit var nextBtn: View

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                verticalMargin = dip(20)
            }
            gravity = Gravity.CENTER_HORIZONTAL
            orientation = LinearLayout.HORIZONTAL
            button {
                prevBtn = this
                backgroundResource = R.drawable.nav_arrow_left
            }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT) { marginEnd = dip(8) }
            button {
                nextBtn = this
                backgroundResource = R.drawable.nav_arrow_right
            }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT) { marginStart = dip(8) }
        }
    }
}