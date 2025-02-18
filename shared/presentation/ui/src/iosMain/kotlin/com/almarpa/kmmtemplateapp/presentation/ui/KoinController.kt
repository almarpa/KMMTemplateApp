package com.almarpa.kmmtemplateapp.presentation.ui

import com.almarpa.kmmtemplateapp.presentation.ui.di.ViewModelsInjector
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            ViewModelsInjector.modules
        )
    }
}
