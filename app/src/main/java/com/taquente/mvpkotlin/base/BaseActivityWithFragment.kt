package com.taquente.mvpkotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.taquente.mvpkotlin.R
import kotlinx.android.synthetic.main.activity_single_fragment.*

/**
 * Base implementation for an activity that shows a single fragment.
 * At this stage it simply extends {@link AppCompatActivity}.
 * If needed to inject dependencies then it should extend {@link DaggerAppCompatActivity}.
 */
abstract class BaseActivityWithFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fl_content, getFragment()).commitNow()
        }
        setSupportActionBar(toolbar)
        title = getActivityTitle()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Should return the fragment to be shown on the activity.
     */
    abstract fun getFragment(): Fragment

    /**
     * Should return the String with the title that will be shown on Toolbar.
     */
    abstract fun getActivityTitle(): String
}