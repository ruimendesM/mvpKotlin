package com.taquente.hwcodechallenge.dependency

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.taquente.mvpkotlin.base.ApplicationClass
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Overrides ApplicationClass for UI tests.
 */
class UITestApplicationClass : ApplicationClass() {

    private val activityList: ArrayList<Activity> = ArrayList()

    lateinit var applicationComponent: UITestsApplicationComponent

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(UITestsActivityCallback())

    }

    /**
     * Override application injector to replace with modules with our mocks.
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = DaggerUITestsApplicationComponent.builder().application(this).build()
        applicationComponent.inject(this)
        return applicationComponent
    }

    fun getActivityOnTop(): Activity? {
        return if (activityList.isEmpty()) null else activityList[activityList.size - 1]
    }


    /**
     * Helper class to maintain a list of activities useful for UI Tests.
     */
    private inner class UITestsActivityCallback : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            activityList.add(activity)
        }

        override fun onActivityStarted(activity: Activity) {}

        override fun onActivityResumed(activity: Activity) {}

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivityStopped(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {}

        override fun onActivityDestroyed(activity: Activity) {
            activityList.remove(activity)
        }
    }
}