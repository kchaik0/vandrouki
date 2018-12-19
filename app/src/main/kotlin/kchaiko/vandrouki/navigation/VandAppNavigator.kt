package kchaiko.vandrouki.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import kchaiko.vandrouki.navigation.androidx.AppNavigator

class VandAppNavigator(activity: FragmentActivity, @IdRes containerId: Int) : AppNavigator(activity, containerId)