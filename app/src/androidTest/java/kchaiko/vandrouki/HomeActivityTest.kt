package kchaiko.vandrouki

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import kchaiko.vandrouki.ui.activity.HomeActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    val activityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Test
    fun generalUI() {
        onView(withId(R.id.am_recycler)).check(matches(not(doesNotExist())))
    }

}