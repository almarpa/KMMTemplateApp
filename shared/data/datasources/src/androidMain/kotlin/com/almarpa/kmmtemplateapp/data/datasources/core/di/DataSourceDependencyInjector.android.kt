package com.almarpa.kmmtemplateapp.data.datasources.core.di

import com.almarpa.kmmtemplateapp.data.datasources.core.local.datastore.dataStore
import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.DatabaseFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun getPlatformInjects(): List<Module> = listOf(
    module {
        // Ktor
        single<HttpClientEngine> { OkHttp.create() }

        // Room
        single { DatabaseFactory(androidApplication()) }

        // DataStore
        singleOf(::dataStore)
    }
)
