package com.almarpa.kmmtemplateapp.data.datasources.core.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import com.almarpa.kmmtemplateapp.data.datasources.core.local.preferences.AppPreferences
import com.almarpa.kmmtemplateapp.data.datasources.core.local.preferences.AppPreferencesImpl
import com.almarpa.kmmtemplateapp.data.datasources.core.remote.PokemonApi
import com.almarpa.kmmtemplateapp.data.datasources.core.remote.createPokemonApi
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun getPlatformInjects(): List<Module>

object DataSourceDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = getPlatformInjects().union(
            listOf(
                module {
                    single<PokemonApi> {
                        Ktorfit
                            .Builder()
                            .baseUrl(PokemonApi.BASE_URL)
                            .build()
                            .createPokemonApi()
                    }
                    single<AppPreferences> { AppPreferencesImpl(get()) }
                }
            )
        ).toList()
}
