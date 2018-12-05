package kchaiko.vandrouki.navigation

import android.support.annotation.IdRes
import android.support.v4.app.FragmentActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class VandAppNavigator(activity: FragmentActivity, @IdRes containerId: Int) : SupportAppNavigator(activity, containerId)