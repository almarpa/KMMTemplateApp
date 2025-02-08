package com.almarpa.kmmtemplateapp.presentation.ui

import com.almarpa.kmmtemplateapp.presentation.ui.di.PresentationViewModelDependencyInjector
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            PresentationViewModelDependencyInjector.modules
        )
    }
}
