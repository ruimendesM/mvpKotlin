package com.taquente.mvpkotlin.conditions

import android.app.Activity
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.util.TreeIterables
import android.view.View
import com.taquente.mvpkotlin.dependency.UITestApplicationClass
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * Condition to ve used to match Views.
 */
class ViewCondition(private val conditionMatcher: Matcher<out View>, private val viewAssertion: Matcher<out View>) : Condition {

    private var view: View? = null

    override fun checkCondition(): Boolean {
        val currentActivity = (InstrumentationRegistry.getTargetContext().applicationContext as UITestApplicationClass).getActivityOnTop()
                ?: return false

        view = findViewOnActivity(currentActivity)

        return view != null && viewAssertion.matches(view)
    }

    override fun getDescription(): String {
        val currentActivity = (InstrumentationRegistry.getTargetContext().applicationContext as UITestApplicationClass).getActivityOnTop()
                ?: return "There is no activity to use for view condition!"

        val description = StringDescription()

        if (view == null) {
            description.appendText("View not found!" + "\r\n")
        } else {
            description.appendText("Found view: " + currentActivity.resources.getResourceName(view!!.id))
        }

        if (!viewAssertion.matches(view)) {
            description.appendText("ViewAssertion did not match the found View: ")
            viewAssertion.describeTo(description)
            description.appendText("\r\n")
        }

        return description.toString()
    }

    private fun findViewOnActivity(activity: Activity): View? {
        val rootView = activity.window.decorView
        var matchingView: View? = null

        for (view in TreeIterables.breadthFirstViewTraversal(rootView)) {
            if (conditionMatcher.matches(view)) {
                if (matchingView != null) {
                    throw AssertionError("There are at least two views that match the condition: v1 => " + matchingView
                            + " | v2 => " + view)
                }
                matchingView = view
            }
        }
        return matchingView
    }

}