package com.taquente.mvpkotlin.utils

import android.support.test.espresso.PerformException
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.util.HumanReadables
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

/**
 * Class with ViewActions for RecyclerView.
 */
class CustomRecyclerViewActions {

    /**
     * This action needs to be called before checking some matching condition on a specific item
     * on the recycler view to prevent the item of being "hidden" and therefore null.
     */
    class ScrollToRecyclerViewItemAt(private val mPosition: Int) : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return allOf<View>(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun getDescription(): String {
            return "scroll RecyclerView to position: $mPosition"
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            recyclerView.scrollToPosition(mPosition)
            uiController.loopMainThreadUntilIdle()
        }
    }

    /**
     * Perform a given action on the nth item of the recycler view.
     */
    class ActionOnNthItem(private val mNthItem: Int, private val mViewAction: ViewAction) : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun getDescription(): String {
            return "Performing " + mViewAction.description
        }

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView

            val adapter = recyclerView.adapter

            if (adapter.itemCount == 0) {
                throw PerformException.Builder().withActionDescription(this.toString())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(IllegalStateException("The RecyclerView is empty!"))
                        .build()
            } else if (adapter.itemCount <= mNthItem) {
                throw PerformException.Builder().withActionDescription(this.toString())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(IllegalStateException("Nth item is out of bounds!"))
                        .build()
            }

            ScrollToRecyclerViewItemAt(mNthItem).perform(uiController, view)
            val viewAtPosition = recyclerView.findViewHolderForAdapterPosition(mNthItem).itemView
            mViewAction.perform(uiController, viewAtPosition)
        }
    }
}