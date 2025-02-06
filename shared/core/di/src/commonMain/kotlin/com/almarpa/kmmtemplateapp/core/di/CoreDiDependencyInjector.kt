package com.almarpa.kmmtemplateapp.core.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import org.koin.core.module.Module

object CoreDiDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = emptyList()
            // TODO:
//            DataSourceCoreDependencyInjector.modules,
//            DataRepositoryDependencyInjector.modules,
//            DomainUseCasesDependencyInjector.modules,
//            PresentationViewModelsDependencyInjector.modules,
//        ).flatten()
}
