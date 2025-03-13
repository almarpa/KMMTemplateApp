package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.Result
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.map
import com.almarpa.kmmtemplateapp.data.datasources.local.features.PokemonDetailsLocalDataSource
import com.almarpa.kmmtemplateapp.data.datasources.remote.features.PokemonRemoteDataSource
import com.almarpa.kmmtemplateapp.data.repository.mappers.toDomain
import com.almarpa.kmmtemplateapp.data.repository.mappers.toEntity
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class PokemonDetailsRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonDetailsLocalDataSource: PokemonDetailsLocalDataSource,
) : PokemonDetailsRepository {

    override suspend fun getPokemonDetails(pokemonID: Int): Result<PokemonDetails, AppError> {
        return withContext(Dispatchers.IO) {
            getLocalPokemonDetails(pokemonID)?.let { localPokemonDetails ->
                Result.Success(localPokemonDetails)
            } ?: run {
                getRemotePokemonDetails(pokemonID)
            }
        }
    }

    private suspend fun getLocalPokemonDetails(pokemonID: Int): PokemonDetails? =
        pokemonDetailsLocalDataSource.getPokemonDetails(pokemonID.toString())?.toDomain()


    private suspend fun getRemotePokemonDetails(pokemonID: Int): Result<PokemonDetails, AppError> =
        pokemonRemoteDataSource.getPokemonDetails(pokemonID).map { remoteResponse ->
            remoteResponse.toDomain().also { savePokemonDetails(it) }
        }

    private suspend fun savePokemonDetails(pokemonDetails: PokemonDetails) =
        pokemonDetailsLocalDataSource.savePokemonDetails(pokemonDetails.toEntity())
}
