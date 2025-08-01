package com.almarpa.kmmtemplateapp.data.datasources.remote.api

import com.almarpa.kmmtemplateapp.data.datasources.models.response.PokemonResultResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.statement.HttpResponse

interface PokemonApi {

    companion object {
        const val BASE_URL = "https://pokeapi.co/"
    }

    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int? = 2000,
        @Query("offset") offset: Int? = 0,
    ): PokemonResultResponse

    @GET("api/v2/pokemon/{pokemonID}")
    suspend fun getPokemon(
        @Path("pokemonID") pokemonId: Int
    ): HttpResponse
}
