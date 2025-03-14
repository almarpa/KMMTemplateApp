package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.core.common.model.entities.onError
import com.almarpa.kmmtemplateapp.core.common.model.entities.onSuccess
import com.almarpa.kmmtemplateapp.data.datasources.local.features.PokemonLocalDataSource
import com.almarpa.kmmtemplateapp.data.datasources.remote.features.PokemonRemoteDataSource
import com.almarpa.kmmtemplateapp.data.repository.mappers.toDomain
import com.almarpa.kmmtemplateapp.data.repository.mappers.toEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override fun fetchPokemonList(): Flow<List<Pokemon>> = flow {
        pokemonLocalDataSource.fetchPokemons().collect { localPokemonList ->
            if (localPokemonList.isNotEmpty()) {
                emit(localPokemonList.map { pokemonEntity -> pokemonEntity.toDomain() })
            } else {
                pokemonRemoteDataSource.fetchPokemons()
                    .onSuccess { pokemonResult ->
                        pokemonResult.results.map { remotePokemon ->
                            remotePokemon.toDomain()
                        }.also { pokemonList ->
                            pokemonLocalDataSource.savePokemons(pokemonList.map { it.toEntity() })
                        }
                    }.onError { e ->
                        // TODO: pass Result in flow
                        throw (e)
                    }
            }
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

