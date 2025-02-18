package com.almarpa.kmmtemplateapp.data.datasources.remote.features

import com.almarpa.kmmtemplateapp.data.models.response.PokemonDetailsResponse
import com.almarpa.kmmtemplateapp.data.models.response.PokemonResponse

interface PokemonRemoteDataSource {
    suspend fun fetchPokemons(): List<PokemonResponse>
    suspend fun getPokemonDetails(pokemonID: Int): PokemonDetailsResponse
}