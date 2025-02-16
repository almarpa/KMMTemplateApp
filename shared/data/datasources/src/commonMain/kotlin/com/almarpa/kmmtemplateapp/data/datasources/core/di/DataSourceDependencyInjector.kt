package com.almarpa.kmmtemplateapp.data.datasources.core.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.DatabaseFactory
import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.PokemonDataBase
import com.almarpa.kmmtemplateapp.data.datasources.core.local.preferences.AppPreferences
import com.almarpa.kmmtemplateapp.data.datasources.core.local.preferences.AppPreferencesImpl
import com.almarpa.kmmtemplateapp.data.datasources.core.remote.api.PokemonApi
import com.almarpa.kmmtemplateapp.data.datasources.core.remote.api.createPokemonApi
import com.almarpa.kmmtemplateapp.data.datasources.core.remote.httpclient.HttpClientFactory
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun getPlatformInjects(): List<Module>

object DataSourceDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = getPlatformInjects().union(commonInjects).toList()

    private val commonInjects = listOf(
        module {
            // Ktor
            single<PokemonApi> {
                Ktorfit
                    .Builder()
                    .httpClient(HttpClientFactory.create(get()))
                    .baseUrl(PokemonApi.BASE_URL)
                    .build()
                    .createPokemonApi()
            }

            // Room
            single {
                get<DatabaseFactory>().create()
                    .setDriver(BundledSQLiteDriver())
                    .build()
            }

            // DAO
            single { get<PokemonDataBase>().pokemonDao }

            // Preferences
            single<AppPreferences> { AppPreferencesImpl(get()) }
        }
    )
}
