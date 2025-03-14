package com.almarpa.kmmtemplateapp.domain.repository

import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.model.entities.Result
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails

interface PokemonDetailsRepository {
    suspend fun getPokemonDetails(pokemonID: Int): Result<PokemonDetails, AppError>
}
