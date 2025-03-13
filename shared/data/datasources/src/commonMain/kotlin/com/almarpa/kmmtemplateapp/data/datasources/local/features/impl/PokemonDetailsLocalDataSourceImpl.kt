package com.almarpa.kmmtemplateapp.data.datasources.local.features.impl

import com.almarpa.kmmtemplateapp.data.datasources.local.db.dao.PokemonDetailsDao
import com.almarpa.kmmtemplateapp.data.datasources.local.features.PokemonDetailsLocalDataSource
import com.almarpa.kmmtemplateapp.data.datasources.models.entities.PokemonDetailsEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokemonDetailsLocalDataSourceImpl(
    private val dao: PokemonDetailsDao,
    private val ioDispatcher: CoroutineDispatcher
) : PokemonDetailsLocalDataSource {

    override suspend fun getPokemonDetails(pokemonId: String): PokemonDetailsEntity? {
        return withContext(ioDispatcher) {
            dao.get(pokemonId)
        }
    }

    override suspend fun savePokemonDetails(pokemonDetails: PokemonDetailsEntity) {
        withContext(ioDispatcher) {
            dao.insert(pokemonDetails)
        }
    }
}