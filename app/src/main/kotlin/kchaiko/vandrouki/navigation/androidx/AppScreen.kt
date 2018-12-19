package kchaiko.vandrouki.navigation.androidx

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Screen

open class AppScreen : Screen() {

    open val fragment: Fragment? = null
    open fun getActivityIntent(ctx: Context): Intent? = null

}