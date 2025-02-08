package com.almarpa.kmmtemplateapp.data.datasources.core.di

import com.almarpa.kmmtemplateapp.data.datasources.features.preferences.dataStore
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun getPlatformInjects(): List<Module> = listOf(
    module {
        // TODO: factory { androidContext() }
        singleOf(::dataStore)
    }
)
