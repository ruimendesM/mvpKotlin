package com.taquente.hwcodechallenge.dependency

import com.taquente.mvpkotlin.base.ApplicationClass
import com.taquente.mvpkotlin.base.ApplicationModule
import com.taquente.mvpkotlin.base.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This class will replace the Application Component from the main app.
 * This is useful for replace some of the modules by mock modules.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, FragmentBindingModule::class, ApplicationModule::class])
interface UITestsApplicationComponent : AndroidInjector<UITestApplicationClass> {

    override fun inject(instance: UITestApplicationClass)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ApplicationClass): UITestsApplicationComponent.Builder

        fun build(): UITestsApplicationComponent
    }
}