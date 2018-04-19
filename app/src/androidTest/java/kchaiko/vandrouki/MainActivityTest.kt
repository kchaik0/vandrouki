package kchaiko.vandrouki

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import kchaiko.vandrouki.activity.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun generalUI() {
        onView(withId(R.id.am_recycler)).check(matches(not(doesNotExist())))
    }

}