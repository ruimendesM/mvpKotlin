package com.taquente.mvpkotlin.base

import android.os.Bundle
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Base implementation for Presenters.
 */
open class BasePresenter<V : BaseContract.BaseView> : BaseContract.Presenter<V> {

    private lateinit var view: V
    private var isAttached: Boolean = false

    var disposables = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
        isAttached = true
    }

    override fun detachView() {
        isAttached = false
        disposables.clear()
    }

    override fun saveInstance(outState: Bundle) {
        // do nothing. Override if you need to store data on configuration changes.
    }

    override fun restoreInstance(savedInstanceState: Bundle?) {
        // do nothing. Override if you want to restore state after a configuration change.
    }


    fun getView(): V? {
        if (isAttached) {
            return view
        }
        return null
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun <C> applySchedulers(): FlowableTransformer<C, C> {
        return FlowableTransformer { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }

}
