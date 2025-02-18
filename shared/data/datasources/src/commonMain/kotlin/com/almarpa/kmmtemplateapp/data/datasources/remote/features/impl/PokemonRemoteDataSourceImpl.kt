package com.almarpa.kmmtemplateapp.data.datasources.remote.features.impl

import com.almarpa.kmmtemplateapp.data.datasources.remote.api.PokemonApi
import com.almarpa.kmmtemplateapp.data.datasources.remote.features.PokemonRemoteDataSource
import com.almarpa.kmmtemplateapp.data.models.response.PokemonDetailsResponse
import com.almarpa.kmmtemplateapp.data.models.response.PokemonResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokemonRemoteDataSourceImpl(
    private val api: PokemonApi,
    private val ioDispatcher: CoroutineDispatcher
) : PokemonRemoteDataSource {

    companion object {
        private const val POKEMON_RESULTS_LIMIT = 1302
        private const val POKEMON_RESULTS_OFFSET = 0
    }

    override suspend fun fetchPokemons(): List<PokemonResponse> {
        return withContext(ioDispatcher) {
            try {
                api.getPokemons(POKEMON_RESULTS_LIMIT, POKEMON_RESULTS_OFFSET).results
            } catch (e: Exception) {
                e.printStackTrace()
                throw e // TODO: create Error Handler
            }
        }
    }

    override suspend fun getPokemonDetails(pokemonID: Int): PokemonDetailsResponse {
        return withContext(ioDispatcher) {
            try {
                api.getPokemon(pokemonID)
            } catch (e: Exception) {
                e.printStackTrace()
                throw e // TODO: create Error Handler
            }
        }
    }
}