package com.almarpa.kmmtemplateapp

import android.app.Application
import com.almarpa.kmmtemplateapp.presentation.ui.di.PresentationUiDependencyInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class CustomApplication : Application() {
    override fun onCreate() {
        GlobalContext.startKoin {
            androidContext(this@CustomApplication)
            modules(PresentationUiDependencyInjector.modules)
        }

        super.onCreate()
    }
}