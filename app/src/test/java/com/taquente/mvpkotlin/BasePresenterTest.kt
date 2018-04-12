package com.taquente.mvpkotlin

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase
import org.mockito.MockitoAnnotations


abstract class BasePresenterTest<T : Any> : TestCase() {

    lateinit var presenter: T

    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        mockRxJavaSchedulers()
        presenter = buildPresenter()
    }

    private fun mockRxJavaSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    abstract fun buildPresenter(): T

}