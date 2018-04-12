package com.taquente.mvpkotlin.base

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger Module to provide application related classes.
 */
@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(application: ApplicationClass): Context = application.applicationContext

    @Provides
    @Singleton
    fun getResources(application: ApplicationClass): Resources = application.resources
}
