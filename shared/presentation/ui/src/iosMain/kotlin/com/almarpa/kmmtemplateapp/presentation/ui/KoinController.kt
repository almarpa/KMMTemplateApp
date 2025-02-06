package com.almarpa.kmmtemplateapp.presentation.ui

import com.almarpa.kmmtemplateapp.presentation.ui.di.PresentationUiDependencyInjector
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            PresentationUiDependencyInjector.modules
        )
    }
}
