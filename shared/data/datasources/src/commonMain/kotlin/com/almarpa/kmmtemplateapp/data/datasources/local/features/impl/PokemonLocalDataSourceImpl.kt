package com.almarpa.kmmtemplateapp.data.datasources.local.features.impl

import com.almarpa.kmmtemplateapp.data.datasources.local.db.dao.PokemonDao
import com.almarpa.kmmtemplateapp.data.datasources.local.features.PokemonLocalDataSource
import com.almarpa.kmmtemplateapp.data.models.entities.PokemonEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PokemonLocalDataSourceImpl(
    private val dao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher
) : PokemonLocalDataSource {

    override fun fetchPokemons(): Flow<List<PokemonEntity>> =
        dao.getAll().flowOn(ioDispatcher)

    override fun getTeamMembers(): Flow<List<PokemonEntity>> =
        dao.getAllTeamMembers().flowOn(ioDispatcher)

    override fun searchPokemonByName(name: String): Flow<List<PokemonEntity>> =
        dao.searchPokemonByName(name.lowercase()).flowOn(ioDispatcher)

    override suspend fun savePokemons(pokemonList: List<PokemonEntity>) {
        withContext(ioDispatcher) {
            dao.insertAll(pokemonList)
        }
    }

    override suspend fun addPokemonToTeam(pokemon: PokemonEntity) {
        withContext(ioDispatcher) {
            dao.update(pokemon)
        }
    }

    override suspend fun createPokemon(pokemon: PokemonEntity) {
        withContext(ioDispatcher) {
            dao.insert(pokemon)
        }
    }
}