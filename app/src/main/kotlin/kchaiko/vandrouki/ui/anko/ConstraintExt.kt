package kchaiko.vandrouki.ui.anko

import android.view.View
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import org.jetbrains.anko.internals.AnkoInternals

fun ConstraintLayout.applyConstraintSet(init: ConstraintSetBuilder.() -> Unit): ConstraintSet =
        constraintSet(init).also { it.applyTo(this) }

fun ConstraintLayout.constraintSet(init: ConstraintSetBuilder.() -> Unit): ConstraintSet = ConstraintSetBuilder().also { it.clone(this) }.apply(init)

class ConstraintSetBuilder : ConstraintSet() {
    operator fun Int.invoke(init: ViewConstraintBuilder.() -> Unit) {
        ViewConstraintBuilder(this, this@ConstraintSetBuilder).apply(init)
    }

    operator fun View.invoke(init: ViewConstraintBuilder.() -> Unit) = id.invoke(init)

    infix fun Side.of(@IdRes viewId: Int) = when (this) {
        Side.LEFT -> ViewSide.Left(viewId)
        Side.RIGHT -> ViewSide.Right(viewId)
        Side.TOP -> ViewSide.Top(viewId)
        Side.BOTTOM -> ViewSide.Bottom(viewId)
        Side.BASELINE -> ViewSide.Baseline(viewId)
        Side.START -> ViewSide.Start(viewId)
        Side.END -> ViewSide.End(viewId)
    }

    infix fun Side.of(view: View) = this of view.id

    infix fun Pair<ViewSide, Side>.of(@IdRes viewId: Int) = first to (second of viewId)

    infix fun Pair<ViewSide, Side>.of(view: View) = first to (second of view.id)

    infix fun ViewSide.to(targetSide: ViewSide) = Connection.BasicConnection(this, targetSide)

    infix fun Connection.BasicConnection.margin(margin: Int) = Connection.MarginConnection(from, to, margin)

    fun connect(vararg connections: Connection) {
        for (connection in connections) {
            when (connection) {
                is Connection.MarginConnection -> connect(
                        connection.from.viewId,
                        connection.from.sideId,
                        connection.to.viewId,
                        connection.to.sideId,
                        connection.margin
                )
                is Connection.BasicConnection -> connect(
                        connection.from.viewId,
                        connection.from.sideId,
                        connection.to.viewId,
                        connection.to.sideId
                )
            }
        }
    }

    enum class Side {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE,
        START,
        END,
    }

    sealed class ViewSide(@IdRes val viewId: Int) {
        class Left(@IdRes viewId: Int) : ViewSide(viewId)
        class Right(@IdRes viewId: Int) : ViewSide(viewId)
        class Top(@IdRes viewId: Int) : ViewSide(viewId)
        class Bottom(@IdRes viewId: Int) : ViewSide(viewId)
        class Baseline(@IdRes viewId: Int) : ViewSide(viewId)
        class Start(@IdRes viewId: Int) : ViewSide(viewId)
        class End(@IdRes viewId: Int) : ViewSide(viewId)

        val sideId: Int
            get() = when (this) {
                is Left -> ConstraintSet.LEFT
                is Right -> ConstraintSet.RIGHT
                is Top -> ConstraintSet.TOP
                is Bottom -> ConstraintSet.BOTTOM
                is Baseline -> ConstraintSet.BASELINE
                is Start -> ConstraintSet.START
                is End -> ConstraintSet.END
            }
    }

    sealed class Connection(val from: ViewSide, val to: ViewSide) {
        class BasicConnection(from: ViewSide, to: ViewSide) : Connection(from, to)
        class MarginConnection(from: ViewSide, to: ViewSide, val margin: Int) : Connection(from, to)
    }
}

class ViewConstraintBuilder(
        private @IdRes val viewId: Int,
        private val constraintSetBuilder: ConstraintSetBuilder) {

    infix fun Pair<ConstraintSetBuilder.Side, ConstraintSetBuilder.Side>.of(@IdRes targetViewId: Int): ConstraintSetBuilder.Connection.BasicConnection =
            constraintSetBuilder.run { (first of viewId) to (second of targetViewId) }

    infix fun Pair<ConstraintSetBuilder.Side, ConstraintSetBuilder.Side>.of(targetView: View): ConstraintSetBuilder.Connection.BasicConnection = this of targetView.id

    fun clear() {
        constraintSetBuilder.clear(viewId)
    }

    fun clear(sideId: Int) {
        constraintSetBuilder.clear(viewId, sideId)
    }

    fun setMargin(sideId: Int, value: Int) {
        constraintSetBuilder.setMargin(viewId, sideId, value)
    }

    fun setGoneMargin(sideId: Int, value: Int) {
        constraintSetBuilder.setGoneMargin(viewId, sideId, value)
    }

    var horizontalBias: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setHorizontalBias(viewId, value)
        }

    var verticalBias: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setVerticalBias(viewId, value)
        }

    var dimensionRation: String
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setDimensionRatio(viewId, value)
        }

    var visibility: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setVisibility(viewId, value)
        }

    var alpha: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setAlpha(viewId, value)
        }

    var applyElevation: Boolean
        get() = constraintSetBuilder.getApplyElevation(viewId)
        set(value) {
            constraintSetBuilder.setApplyElevation(viewId, value)
        }

    var elevation: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setElevation(viewId, value)
        }

    var rotationX: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setRotationX(viewId, value)
        }

    var rotationY: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setRotationY(viewId, value)
        }

    var scaleX: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setScaleX(viewId, value)
        }

    var scaleY: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setScaleY(viewId, value)
        }

    var transformPivotX: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setTransformPivotX(viewId, value)
        }

    var transformPivotY: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setTransformPivotY(viewId, value)
        }

    var translationX: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setTranslationX(viewId, value)
        }

    var translationY: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setTranslationY(viewId, value)
        }

    var translationZ: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setTranslationZ(viewId, value)
        }

    var height: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainHeight(viewId, value)
        }

    var width: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainWidth(viewId, value)
        }

    var maxHeight: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainMaxHeight(viewId, value)
        }

    var maxWidth: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainMaxWidth(viewId, value)
        }

    var minHeight: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainMinHeight(viewId, value)
        }

    var minWidth: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainMinWidth(viewId, value)
        }

    var defaultHeight: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainDefaultHeight(viewId, value)
        }

    var defaultWidth: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.constrainDefaultWidth(viewId, value)
        }

    var horizontalWeight: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setHorizontalWeight(viewId, value)
        }

    var verticalWeight: Float
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setVerticalWeight(viewId, value)
        }

    var horizontalChainStyle: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setHorizontalChainStyle(viewId, value)
        }

    var verticalChainStyle: Int
        @Deprecated(AnkoInternals.NO_GETTER, level = DeprecationLevel.ERROR) get() = AnkoInternals.noGetter()
        set(value) {
            constraintSetBuilder.setVerticalChainStyle(viewId, value)
        }
}