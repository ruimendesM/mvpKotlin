package com.taquente.mvpkotlin.dependency

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class UITestsRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return Instrumentation.newApplication(UITestApplicationClass::class.java, context)
    }
}