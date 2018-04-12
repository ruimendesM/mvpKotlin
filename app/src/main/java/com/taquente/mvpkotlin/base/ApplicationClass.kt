package com.taquente.mvpkotlin.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Base class for Application.
 */
open class ApplicationClass : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent = DaggerApplicationComponent.builder().application(this).build()
        applicationComponent.inject(this)
        return applicationComponent
    }

}