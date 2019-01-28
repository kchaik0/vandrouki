package kchaiko.vandrouki.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class VandAppNavigator(activity: FragmentActivity, @IdRes containerId: Int) : SupportAppNavigator(activity, containerId)