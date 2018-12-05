package kchaiko.vandrouki

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kchaiko.vandrouki.ui.activity.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<SplashActivity>(SplashActivity::class.java)

    @Test
    fun generalUI() {
        assertEquals("kchaiko.vandrouki", InstrumentationRegistry.getTargetContext().packageName)
    }

}