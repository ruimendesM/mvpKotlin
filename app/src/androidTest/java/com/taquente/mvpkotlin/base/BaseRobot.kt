package com.taquente.mvpkotlin.base

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeRight
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.taquente.mvpkotlin.base.BaseTest
import com.taquente.mvpkotlin.conditions.ViewCondition
import com.taquente.mvpkotlin.utils.UITestUtils.waitForCondition
import org.hamcrest.Matchers.allOf

/**
 * Base robot for all screens.
 * This class contains methods that can be shared between Robots.
 */
open class BaseRobot(val baseTest: BaseTest) {

    enum class SnackbarAction {
        DISMISS,
        CLICK_BUTTON,
        NOTHING
    }

    fun verifySnackbarMessage(@StringRes messageRes: Int, action: SnackbarAction) {
        verifySnackbarMessage(baseTest.getActivity().getString(messageRes), action)
    }

    fun verifySnackbarMessage(message: String, action: SnackbarAction) {
        waitForCondition(ViewCondition(withId(getSnackbarId()), isDisplayingAtLeast(90)))
        onView(allOf<View>(withId(getSnackbarId()), withText(message))).check(matches(isDisplayed()))

        if (action == SnackbarAction.DISMISS) {
            // swipe to dismiss
            onView(withId(getSnackbarId()))
                    .perform(swipeRight())

        } else if (action == SnackbarAction.CLICK_BUTTON) {
            //click on action button
            onView(allOf(withId(getSnackbarButton())))
                    .perform(click())
        }
    }


    /**************
     * RESOURCES
     **************/

    private fun getSnackbarId(): Int {
        return android.support.design.R.id.snackbar_text
    }

    private fun getSnackbarButton(): Int {
        return android.support.design.R.id.snackbar_action
    }

}