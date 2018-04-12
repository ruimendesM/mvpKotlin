package com.taquente.mvpkotlin.utils

import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object MatcherUtils {
    /**
     * This matcher will check if a view has a given visibility.
     */
    fun hasVisibilityAs(visibility: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                val visibilityString = if (visibility == View.GONE) "Gone" else if (visibility == View.INVISIBLE) "Invisible" else "Visible"
                description.appendText("Checking if visibility is: $visibilityString")
            }

            override fun matchesSafely(target: View): Boolean {
                return target.visibility == visibility
            }
        }
    }


    /**
     * This matcher will check if a recycler view's item at a given position matches a given condition.
     */
    fun verifyAt(viewMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Checking " + viewMatcher.toString() + " at ")
                description.appendValue(position)
            }

            override fun matchesSafely(target: View): Boolean {

                if (target is RecyclerView) {

                    val adapter = target.adapter

                    if (adapter.itemCount == 0 || adapter.itemCount <= position) {
                        return false
                    }

                    val viewAtPosition = target.findViewHolderForAdapterPosition(position).itemView
                    return viewMatcher.matches(viewAtPosition)
                }

                return false
            }
        }
    }
}