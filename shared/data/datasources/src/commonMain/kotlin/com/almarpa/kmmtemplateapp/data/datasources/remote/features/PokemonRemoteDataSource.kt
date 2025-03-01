package com.almarpa.kmmtemplateapp.data.datasources.remote.features

import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.Result
import com.almarpa.kmmtemplateapp.data.datasources.models.response.PokemonDetailsResponse
import com.almarpa.kmmtemplateapp.data.datasources.models.response.PokemonResultResponse

interface PokemonRemoteDataSource {
    suspend fun fetchPokemons(): Result<PokemonResultResponse, AppError>
    suspend fun getPokemonDetails(pokemonID: Int): Result<PokemonDetailsResponse, AppError>
}