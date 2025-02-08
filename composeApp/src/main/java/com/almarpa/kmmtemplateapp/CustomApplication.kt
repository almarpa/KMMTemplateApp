package com.almarpa.kmmtemplateapp

import android.app.Application
import com.almarpa.kmmtemplateapp.presentation.ui.di.PresentationViewModelDependencyInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class CustomApplication : Application() {
    override fun onCreate() {
        GlobalContext.startKoin {
            androidContext(this@CustomApplication)
            modules(PresentationViewModelDependencyInjector.modules)
        }

        super.onCreate()
    }
}