package com.almarpa.kmmtemplateapp.data.datasources.core.local.db

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object DatabaseConstructor : RoomDatabaseConstructor<PokemonDataBase> {
    override fun initialize(): PokemonDataBase
}