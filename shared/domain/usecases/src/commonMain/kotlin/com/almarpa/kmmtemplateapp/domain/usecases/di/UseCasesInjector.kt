package com.almarpa.kmmtemplateapp.domain.usecases.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModules
import com.almarpa.kmmtemplateapp.domain.usecases.features.AddPokemonUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.CreateTeamMemberUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonDetailsUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetTeamUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetUserDataUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SearchPokemonUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SetAppLocaleUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SetAppThemeUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object UseCasesInjector : KoinModules {
    override val modules: List<Module>
        get() = listOf(
            module {
                factoryOf(::GetUserDataUseCase)
                factoryOf(::SetAppThemeUseCase)
                factoryOf(::SetAppLocaleUseCase)
                factoryOf(::GetPokemonUseCase)
                factoryOf(::SearchPokemonUseCase)
                factoryOf(::GetPokemonDetailsUseCase)
                factoryOf(::AddPokemonUseCase)
                factoryOf(::GetTeamUseCase)
                factoryOf(::CreateTeamMemberUseCase)
            }
        )
}
