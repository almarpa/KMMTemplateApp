package com.almarpa.kmmtemplateapp.data.datasources.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "createdAt")
    var createdAt: Long = 0L,
    @ColumnInfo(name = "dominantColor")
    var color: Int = 0,
    @ColumnInfo(name = "isTeamMember")
    var isTeamMember: Boolean = false,
)