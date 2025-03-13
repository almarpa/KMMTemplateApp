package com.almarpa.kmmtemplateapp.data.datasources.local.features

import com.almarpa.kmmtemplateapp.data.datasources.models.entities.PokemonDetailsEntity

interface PokemonDetailsLocalDataSource {
    suspend fun getPokemonDetails(pokemonId: String): PokemonDetailsEntity?
    suspend fun savePokemonDetails(pokemonDetails: PokemonDetailsEntity)
}