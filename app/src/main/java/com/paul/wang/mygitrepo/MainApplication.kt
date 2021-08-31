package com.paul.wang.mygitrepo

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application()
class LoggingInitializer : Initializer<Timber.Tree> {
    override fun create(context: Context): Timber.Tree {
        val tree = Timber.DebugTree()
        Timber.plant(tree)
        return tree
    }

    override fun dependencies(): List<Class<out Initializer<Any>>> = emptyList()
}
