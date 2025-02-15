package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.dao.PokemonDao
import com.almarpa.kmmtemplateapp.data.repository.features.mappers.asDomain
import com.almarpa.kmmtemplateapp.data.repository.features.mappers.asEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(private val pokemonDao: PokemonDao) : PokemonRepository {
    override fun fetchPokemonList(): Flow<List<Pokemon>> =
        this.pokemonDao.getAll().map {
            it.map { pokemonEntity -> pokemonEntity.asDomain() }
        }

    override suspend fun addPokemon(pokemon: Pokemon) =
        this.pokemonDao.insert(pokemon.asEntity())
}

