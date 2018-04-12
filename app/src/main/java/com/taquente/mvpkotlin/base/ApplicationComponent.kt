package com.taquente.mvpkotlin.base

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Dagger Component for Application. This is the main component on the project and it is initialized on application start.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, FragmentBindingModule::class, ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<ApplicationClass> {

    override fun inject(instance: ApplicationClass)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ApplicationClass): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }

}
