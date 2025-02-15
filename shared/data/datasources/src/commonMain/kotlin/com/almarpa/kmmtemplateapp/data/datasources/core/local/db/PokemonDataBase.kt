package com.almarpa.kmmtemplateapp.data.datasources.core.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.dao.PokemonDao
import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
@ConstructedBy(PokemonDatabaseConstructor::class)
abstract class PokemonDataBase : RoomDatabase(), DB {
    abstract val pokemonDao: PokemonDao

    override fun clearAllTables() {}

    companion object {
        const val DB_NAME = "pokemonDB.db"
    }
}

interface DB {
    fun clearAllTables() {}
}
