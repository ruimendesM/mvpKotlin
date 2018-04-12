package com.taquente.mvpkotlin.base

import android.support.test.rule.ActivityTestRule
import android.support.v7.app.AppCompatActivity
import com.taquente.mvpkotlin.dependency.UITestApplicationClass
import org.junit.Before
import org.junit.Rule

/**
 * Base class for all tests.
 * Currently all start with SplashActivity.
 */
open class BaseTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(BaseActivityWithFragment::class.java) // TODO replace here for the first activity on the app

    @Before
    open fun setUp() {
        // do nothing
    }

    fun getApplication(): UITestApplicationClass {
        return getActivity().applicationContext as UITestApplicationClass
    }

    fun getActivity(): AppCompatActivity {
        return activityTestRule.activity
    }
}