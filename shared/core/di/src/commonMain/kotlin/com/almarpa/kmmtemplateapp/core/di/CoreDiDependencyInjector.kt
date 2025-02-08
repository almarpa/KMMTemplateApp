package com.almarpa.kmmtemplateapp.core.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import com.almarpa.kmmtemplateapp.data.datasources.core.di.DataSourceDependencyInjector
import com.almarpa.kmmtemplateapp.data.repository.di.DataRepositoryDependencyInjector
import com.almarpa.kmmtemplateapp.domain.usecases.di.DomainUseCasesDependencyInjector
import org.koin.core.module.Module

object CoreDiDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            DataSourceDependencyInjector.modules,
            DataRepositoryDependencyInjector.modules,
            DomainUseCasesDependencyInjector.modules,
        ).flatten()
}
