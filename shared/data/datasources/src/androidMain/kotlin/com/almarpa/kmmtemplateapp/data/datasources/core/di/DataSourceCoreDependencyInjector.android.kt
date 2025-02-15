package com.almarpa.kmmtemplateapp.data.datasources.core.di

import com.almarpa.kmmtemplateapp.data.datasources.core.local.datastore.dataStore
import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun getPlatformInjects(): List<Module> = listOf(
    module {
        // factory { androidContext() }

        singleOf(::dataStore)
        single { DatabaseFactory(androidApplication()) }
    }
)
