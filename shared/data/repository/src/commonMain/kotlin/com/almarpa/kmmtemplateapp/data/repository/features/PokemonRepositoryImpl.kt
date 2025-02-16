package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.dao.PokemonDao
import com.almarpa.kmmtemplateapp.data.datasources.core.remote.api.PokemonApi
import com.almarpa.kmmtemplateapp.data.repository.features.mappers.map
import com.almarpa.kmmtemplateapp.data.repository.features.mappers.toDomain
import com.almarpa.kmmtemplateapp.data.repository.features.mappers.toEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PokemonRepositoryImpl(
    private val api: PokemonApi,
    private val dao: PokemonDao,
) : PokemonRepository {

    companion object {
        private const val POKEMON_RESULTS_LIMIT = 1302
        private const val POKEMON_RESULTS_OFFSET = 0
        private const val REMOTE_REFRESHING_DAYS = 7L
    }

    override fun fetchPokemonList(): Flow<List<Pokemon>> = flow {
        val localData = dao.getAll().firstOrNull()
        if (localData.isNullOrEmpty()) {
            emit(localData!!.map { it.toDomain() })
            return@flow
        }

        try {
            val response = api.getPokemons(POKEMON_RESULTS_LIMIT, POKEMON_RESULTS_OFFSET)
            val pokemonList = response.map().results.map { it.toEntity() }
            dao.insertAll(pokemonList)
            emit(pokemonList.map { it.toDomain() })
        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun addPokemon(pokemon: Pokemon) =
        this.dao.insert(pokemon.toEntity())
}

