package com.almarpa.kmmtemplateapp.domain.usecases.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import com.almarpa.kmmtemplateapp.domain.usecases.features.preferences.GetDeviceIdUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.preferences.SetDeviceIdUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object DomainUseCasesDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                factoryOf(::GetDeviceIdUseCase)
                factoryOf(::SetDeviceIdUseCase)
            }
        )
}
