package com.almarpa.kmmtemplateapp.data.datasources.core.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT min(createdAt) from pokemon")
    suspend fun getCreationDate(): Long?

    @Query("SELECT * from pokemon")
    fun getAll(): Flow<List<PokemonEntity>>

    @Query("SELECT * from pokemon WHERE isTeamMember")
    fun getAllTeamMembers(): Flow<List<PokemonEntity>>

    @Query("SELECT * from pokemon WHERE name LIKE '%' || :name || '%'")
    fun searchPokemonByName(name: String): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Update
    suspend fun update(pokemon: PokemonEntity)

    @Delete
    suspend fun delete(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()
}
