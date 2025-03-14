package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.model.entities.Result
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.domain.repository.PokemonDetailsRepository

class GetPokemonDetailsUseCase(private val repository: PokemonDetailsRepository) {
    suspend operator fun invoke(pokemonId: Int): Result<PokemonDetails, AppError> =
        this.repository.getPokemonDetails(pokemonId)
}
