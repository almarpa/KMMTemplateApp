package com.almarpa.kmmtemplateapp.data.datasources.core.local.db

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PokemonDatabaseConstructor : RoomDatabaseConstructor<PokemonDataBase> {
    override fun initialize(): PokemonDataBase
}