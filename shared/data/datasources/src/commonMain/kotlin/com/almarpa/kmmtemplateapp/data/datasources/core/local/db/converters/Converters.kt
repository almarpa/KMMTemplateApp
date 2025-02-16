package com.almarpa.kmmtemplateapp.data.datasources.core.local.db.converters

import androidx.room.TypeConverter
import com.almarpa.kmmtemplateapp.domain.models.Move
import com.almarpa.kmmtemplateapp.domain.models.Stat
import com.almarpa.kmmtemplateapp.domain.models.TypeX
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun statListToString(statList: List<Stat>): String {
        return Json.encodeToString(statList)
    }

    @TypeConverter
    fun stringToStatList(value: String): List<Stat> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun typeListToString(typeList: List<TypeX>): String {
        return Json.encodeToString(typeList)
    }

    @TypeConverter
    fun stringToTypeList(value: String): List<TypeX> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun moveListToString(moveList: List<Move>): String {
        return Json.encodeToString(moveList)
    }

    @TypeConverter
    fun stringToMoveList(value: String): List<Move> {
        return Json.decodeFromString(value)
    }
}