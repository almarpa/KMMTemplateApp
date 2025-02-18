package com.almarpa.kmmtemplateapp.data.datasources.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.almarpa.kmmtemplateapp.data.datasources.local.db.converters.Converters
import com.almarpa.kmmtemplateapp.data.datasources.local.db.dao.PokemonDao
import com.almarpa.kmmtemplateapp.data.models.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
@ConstructedBy(DatabaseConstructor::class)
abstract class PokemonDataBase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao

    companion object {
        const val DB_NAME = "pokemonDB.db"
    }
}