package com.almarpa.kmmtemplateapp.presentation.ui.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import com.almarpa.kmmtemplateapp.core.di.CoreDiDependencyInjector
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.DeviceViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object PresentationViewModelDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            CoreDiDependencyInjector.modules,
            listOf(
                module {
                    factoryOf(::DeviceViewModel)
                    factoryOf(::PokemonViewModel)
                }
            )
        ).flatten()

}