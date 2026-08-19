package com.almarpa.kmmtemplateapp.data.datasources.remote.api

import com.almarpa.kmmtemplateapp.data.datasources.models.response.PokemonResultResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

interface PokemonApi {

    companion object {
        const val BASE_URL = "https://pokeapi.co/"
    }

    suspend fun getPokemons(
        limit: Int? = 2000,
        offset: Int? = 0,
    ): PokemonResultResponse

    suspend fun getPokemon(
        pokemonId: Int
    ): HttpResponse
}

class PokemonApiImpl(private val client: HttpClient) : PokemonApi {

    override suspend fun getPokemons(limit: Int?, offset: Int?): PokemonResultResponse {
        return client.get("${PokemonApi.BASE_URL}api/v2/pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    }

    override suspend fun getPokemon(pokemonId: Int): HttpResponse {
        return client.get("${PokemonApi.BASE_URL}api/v2/pokemon/$pokemonId")
    }
}
