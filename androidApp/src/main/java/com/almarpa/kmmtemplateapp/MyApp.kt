package com.almarpa.kmmtemplateapp

import android.app.Application
import com.almarpa.kmmtemplateapp.presentation.ui.di.ViewModelsInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApp : Application() {
    override fun onCreate() {
        GlobalContext.startKoin {
            androidContext(this@MyApp)
            modules(ViewModelsInjector.modules)
        }

        super.onCreate()
    }
}