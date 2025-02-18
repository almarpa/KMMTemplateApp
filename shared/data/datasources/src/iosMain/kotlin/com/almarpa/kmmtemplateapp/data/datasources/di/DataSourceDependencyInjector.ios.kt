package com.almarpa.kmmtemplateapp.data.datasources.di

import com.almarpa.kmmtemplateapp.data.datasources.local.datastore.dataStore
import com.almarpa.kmmtemplateapp.data.datasources.local.db.DatabaseFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun getPlatformInjects(): List<Module> = listOf(
    module {
        // Ktor
        single<HttpClientEngine> { Darwin.create() }

        // Room
        single { DatabaseFactory() }

        // DataStore
        singleOf(::dataStore)
    }
)
