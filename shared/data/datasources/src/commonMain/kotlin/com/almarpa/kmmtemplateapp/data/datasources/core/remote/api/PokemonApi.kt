package com.almarpa.kmmtemplateapp.data.datasources.core.remote.api

import com.almarpa.kmmtemplateapp.data.models.response.PokemonDetailsResponse
import com.almarpa.kmmtemplateapp.data.models.response.PokemonResultResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface PokemonApi {

    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int? = 2000,
        @Query("offset") offset: Int? = 0,
    ): PokemonResultResponse

    @GET("api/v2/pokemon/{pokemonID}")
    fun getPokemon(@Path("pokemonID") pokemonId: Int): PokemonDetailsResponse

    companion object {
        const val BASE_URL = "https://pokeapi.co/"
    }
}
