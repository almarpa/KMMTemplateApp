package com.almarpa.kmmtemplateapp.core.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModuleLoader
import com.almarpa.kmmtemplateapp.data.datasources.core.di.DataSourceCoreDependencyInjector
import com.almarpa.kmmtemplateapp.data.repository.di.DataRepositoryDependencyInjector
import com.almarpa.kmmtemplateapp.domain.usecases.di.DomainUseCasesDependencyInjector
import com.almarpa.kmmtemplateapp.presentation.viewmodels.di.PresentationViewModelsDependencyInjector
import org.koin.core.module.Module

object CoreDiDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            DataSourceCoreDependencyInjector.modules,
            DataRepositoryDependencyInjector.modules,
            DomainUseCasesDependencyInjector.modules,
            PresentationViewModelsDependencyInjector.modules,
        ).flatten()
}
