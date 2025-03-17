package com.almarpa.kmmtemplateapp.domain.usecases.di

import com.almarpa.kmmtemplateapp.core.common.di.KoinModules
import com.almarpa.kmmtemplateapp.domain.usecases.features.AddPokemonToTeamUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.CreateTeamMemberUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.FetchPokemonUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.FetchTeamMembersUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.FetchUserDataUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonDetailsUseCase
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
                factoryOf(::FetchUserDataUseCase)
                factoryOf(::SetAppThemeUseCase)
                factoryOf(::SetAppLocaleUseCase)
                factoryOf(::FetchPokemonUseCase)
                factoryOf(::SearchPokemonUseCase)
                factoryOf(::GetPokemonDetailsUseCase)
                factoryOf(::AddPokemonToTeamUseCase)
                factoryOf(::FetchTeamMembersUseCase)
                factoryOf(::CreateTeamMemberUseCase)
            }
        )
}
