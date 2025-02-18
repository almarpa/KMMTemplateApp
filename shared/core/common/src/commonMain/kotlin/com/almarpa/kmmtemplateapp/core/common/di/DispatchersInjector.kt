package com.almarpa.kmmtemplateapp.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

object DispatchersInjector : KoinModules {
    override val modules: List<Module>
        get() = listOf(
            module {
                single<CoroutineDispatcher> { Dispatchers.Main }
                single<CoroutineDispatcher> { Dispatchers.Default }
                single<CoroutineDispatcher> { Dispatchers.IO }
            }
        )
}