package com.taquente.mvpkotlin.base

import android.os.Bundle

/**
 * Base contract between Presenter and View interfaces.
 */
class BaseContract {
    interface Presenter<in V> {
        fun attachView(view: V)
        
        fun detachView()

        fun saveInstance(outState: Bundle)

        fun restoreInstance(savedInstanceState: Bundle?)
    }

    interface BaseView {
        fun logDebug(tag: String, message: String)

        fun logError(tag: String, message: String)
    }
}