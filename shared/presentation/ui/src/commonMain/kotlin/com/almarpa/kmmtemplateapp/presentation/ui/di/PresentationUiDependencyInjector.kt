package com.almarpa.kmmtemplateapp.presentation.ui.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import com.almarpa.kmmtemplateapp.core.di.CoreDiDependencyInjector
import org.koin.core.module.Module

object PresentationUiDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            CoreDiDependencyInjector.modules,
            listOf()
        ).flatten()

}