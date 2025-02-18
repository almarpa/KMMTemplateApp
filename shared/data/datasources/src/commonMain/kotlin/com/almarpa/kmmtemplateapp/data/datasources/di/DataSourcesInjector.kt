package com.almarpa.kmmtemplateapp.data.datasources.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.almarpa.kmmtemplateapp.core.common.di.KoinModules
import com.almarpa.kmmtemplateapp.data.datasources.local.db.DatabaseFactory
import com.almarpa.kmmtemplateapp.data.datasources.local.db.PokemonDataBase
import com.almarpa.kmmtemplateapp.data.datasources.local.features.DataStoreSource
import com.almarpa.kmmtemplateapp.data.datasources.local.features.PokemonLocalDataSource
import com.almarpa.kmmtemplateapp.data.datasources.local.features.impl.DataStoreSourceImpl
import com.almarpa.kmmtemplateapp.data.datasources.local.features.impl.PokemonLocalDataSourceImpl
import com.almarpa.kmmtemplateapp.data.datasources.remote.api.PokemonApi
import com.almarpa.kmmtemplateapp.data.datasources.remote.api.createPokemonApi
import com.almarpa.kmmtemplateapp.data.datasources.remote.features.PokemonRemoteDataSource
import com.almarpa.kmmtemplateapp.data.datasources.remote.features.impl.PokemonRemoteDataSourceImpl
import com.almarpa.kmmtemplateapp.data.datasources.remote.httpclient.HttpClientFactory
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun getPlatformInjects(): List<Module>

object DataSourcesInjector : KoinModules {
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
            single<DataStoreSource> { DataStoreSourceImpl(get()) }

            // DataSources
            factoryOf(::PokemonRemoteDataSourceImpl) bind PokemonRemoteDataSource::class
            factoryOf(::PokemonLocalDataSourceImpl) bind PokemonLocalDataSource::class
        }
    )
}
