package com.almarpa.kmmtemplateapp.data.repository.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModules
import com.almarpa.kmmtemplateapp.data.repository.features.PokemonDetailsRepositoryImpl
import com.almarpa.kmmtemplateapp.data.repository.features.PokemonRepositoryImpl
import com.almarpa.kmmtemplateapp.data.repository.features.PreferencesRepositoryImpl
import com.almarpa.kmmtemplateapp.domain.repository.PokemonDetailsRepository
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository
import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object RepositoriesInjector : KoinModules {
    override val modules: List<Module>
        get() = listOf(
            module {
                factoryOf(::PreferencesRepositoryImpl) bind PreferencesRepository::class
                factoryOf(::PokemonRepositoryImpl) bind PokemonRepository::class
                factoryOf(::PokemonDetailsRepositoryImpl) bind PokemonDetailsRepository::class
                factoryOf(::PreferencesRepositoryImpl) bind PreferencesRepository::class
            }
        )
}
