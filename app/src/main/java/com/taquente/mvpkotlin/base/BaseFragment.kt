package com.taquente.mvpkotlin.base

import android.os.Bundle
import android.util.Log
import android.view.View
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Base implementation for all fragments.
 */
abstract class BaseFragment<in V : BaseContract.BaseView, P : BaseContract.Presenter<V>> : DaggerFragment(), BaseContract.BaseView {

    @Inject
    lateinit var presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        presenter.restoreInstance(savedInstanceState)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter.saveInstance(outState)
        super.onSaveInstanceState(outState)
    }

    override fun logDebug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun logError(tag: String, message: String) {
        Log.e(tag, message)
    }
}
