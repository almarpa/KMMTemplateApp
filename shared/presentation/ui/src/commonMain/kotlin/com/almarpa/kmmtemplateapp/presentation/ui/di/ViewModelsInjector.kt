package com.almarpa.kmmtemplateapp.presentation.ui.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModules
import com.almarpa.kmmtemplateapp.core.di.CoreInjector
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonListViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsViewModel
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object ViewModelsInjector : KoinModules {
    override val modules: List<Module>
        get() = listOf(
            CoreInjector.modules,
            listOf(
                module {
                    factoryOf(::SettingsViewModel)
                    factoryOf(::PokemonListViewModel)
                    factoryOf(::PokemonDetailsViewModel)
                    factoryOf(::TeamViewModel)
                }
            )
        ).flatten()
}