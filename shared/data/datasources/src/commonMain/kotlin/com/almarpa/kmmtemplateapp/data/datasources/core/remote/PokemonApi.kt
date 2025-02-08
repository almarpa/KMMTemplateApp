package com.almarpa.kmmtemplateapp.data.datasources.core.remote

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 10000,
        @Query("offset") offset: Int = 0,
    ): Any

    companion object {
        const val BASE_URL = "https://pokeapi.co"
    }
}
