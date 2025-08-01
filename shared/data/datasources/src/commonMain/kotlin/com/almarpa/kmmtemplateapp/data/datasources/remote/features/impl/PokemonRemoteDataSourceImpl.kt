package com.almarpa.kmmtemplateapp.data.datasources.remote.features.impl

import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.extensions.safeCall
import com.almarpa.kmmtemplateapp.core.common.model.entities.Result
import com.almarpa.kmmtemplateapp.data.datasources.models.response.PokemonDetailsResponse
import com.almarpa.kmmtemplateapp.data.datasources.models.response.PokemonResultResponse
import com.almarpa.kmmtemplateapp.data.datasources.remote.api.PokemonApi
import com.almarpa.kmmtemplateapp.data.datasources.remote.features.PokemonRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokemonRemoteDataSourceImpl(
    private val api: PokemonApi,
    private val ioDispatcher: CoroutineDispatcher,
) : PokemonRemoteDataSource {

    companion object {
        private const val POKEMON_RESULTS_LIMIT = 1302
        private const val POKEMON_RESULTS_OFFSET = 0
    }

    override suspend fun fetchPokemons(): PokemonResultResponse =
        withContext(ioDispatcher) {
            api.getPokemons(POKEMON_RESULTS_LIMIT, POKEMON_RESULTS_OFFSET)
        }

    override suspend fun getPokemonDetails(pokemonID: Int): Result<PokemonDetailsResponse, AppError> =
        safeCall<PokemonDetailsResponse> { api.getPokemon(pokemonID) }
}