package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository

class AddPokemonToTeamUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(pokemon: Pokemon) = this.repository.addPokemonToTeam(pokemon)
}