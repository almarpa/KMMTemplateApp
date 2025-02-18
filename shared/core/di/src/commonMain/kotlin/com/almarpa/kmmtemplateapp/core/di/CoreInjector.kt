package com.almarpa.kmmtemplateapp.core.di

import com.almarpa.kmmtemplateapp.core.common.di.DispatchersInjector
import com.almarpa.kmmtemplateapp.core.common.di.KoinModules
import com.almarpa.kmmtemplateapp.data.datasources.di.DataSourcesInjector
import com.almarpa.kmmtemplateapp.data.repository.di.RepositoriesInjector
import com.almarpa.kmmtemplateapp.domain.usecases.di.UseCasesInjector
import org.koin.core.module.Module

object CoreInjector : KoinModules {
    override val modules: List<Module>
        get() = listOf(
            DispatchersInjector.modules,
            DataSourcesInjector.modules,
            RepositoriesInjector.modules,
            UseCasesInjector.modules,
        ).flatten()
}
