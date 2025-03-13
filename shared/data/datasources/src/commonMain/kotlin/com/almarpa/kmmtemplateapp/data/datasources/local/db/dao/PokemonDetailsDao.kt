package com.almarpa.kmmtemplateapp.data.datasources.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.almarpa.kmmtemplateapp.data.datasources.models.entities.PokemonDetailsEntity

@Dao
interface PokemonDetailsDao {

    @Query("SELECT * FROM pokemonDetails WHERE name = :id")
    suspend fun get(id: String): PokemonDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonDetails: PokemonDetailsEntity)

    @Update
    suspend fun update(pokemonDetails: PokemonDetailsEntity)

    @Delete
    suspend fun delete(pokemonDetails: PokemonDetailsEntity)

    @Query("DELETE FROM pokemonDetails")
    suspend fun clearAll()
}
