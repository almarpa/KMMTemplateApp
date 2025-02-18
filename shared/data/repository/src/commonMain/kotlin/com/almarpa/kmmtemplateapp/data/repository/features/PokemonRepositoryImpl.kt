package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.data.datasources.local.features.PokemonLocalDataSource
import com.almarpa.kmmtemplateapp.data.datasources.remote.features.PokemonRemoteDataSource
import com.almarpa.kmmtemplateapp.data.repository.mappers.map
import com.almarpa.kmmtemplateapp.data.repository.mappers.toDomain
import com.almarpa.kmmtemplateapp.data.repository.mappers.toEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override fun fetchPokemonList(): Flow<List<Pokemon>> = flow {
        with(pokemonLocalDataSource.fetchPokemons().firstOrNull()) {
            if (!isNullOrEmpty()) {
                emit(map { it.toDomain() })
                return@flow
            }
        }

        try {
            val pokemonList = pokemonRemoteDataSource.fetchPokemons().map { pokemonResponse ->
                pokemonResponse.map()
            }.also {
                pokemonLocalDataSource.savePokemons(it.map { pokemon -> pokemon.toEntity() })
            }
            emit(pokemonList)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    override fun getTeamMembers(): Flow<List<Pokemon>> =
        pokemonLocalDataSource.getTeamMembers().map { pokemonList ->
            pokemonList.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)

    override fun searchPokemonByName(name: String): Flow<List<Pokemon>> =
        pokemonLocalDataSource.searchPokemonByName(name.lowercase()).map { pokemonList ->
            pokemonList.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)

    override suspend fun addPokemonToTeam(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            pokemonLocalDataSource.addPokemonToTeam(pokemon.toEntity())
        }
    }

    override suspend fun createPokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            pokemonLocalDataSource.createPokemon(pokemon.toEntity())
        }
    }
}

