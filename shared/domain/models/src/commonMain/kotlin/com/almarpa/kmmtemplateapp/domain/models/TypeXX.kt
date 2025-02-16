package com.almarpa.kmmtemplateapp.domain.models

import com.almarpa.kmmtemplateapp.domain.models.enums.PokemonTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class TypeXX(
    val name: PokemonTypeEnum,
    val url: String,
) {
//    fun getColor(): Color {
//        return when (name) {
//            PokemonTypeEnum.NORMAL -> TypeNormal
//            PokemonTypeEnum.FIRE -> TypeFire
//            PokemonTypeEnum.WATER -> TypeWater
//            PokemonTypeEnum.ELECTRIC -> TypeElectric
//            PokemonTypeEnum.GRASS -> TypeGrass
//            PokemonTypeEnum.ICE -> TypeIce
//            PokemonTypeEnum.FIGHTING -> TypeFighting
//            PokemonTypeEnum.POISON -> TypePoison
//            PokemonTypeEnum.GROUND -> TypeGround
//            PokemonTypeEnum.FLYING -> TypeFlying
//            PokemonTypeEnum.PSYCHIC -> TypePsychic
//            PokemonTypeEnum.BUG -> TypeBug
//            PokemonTypeEnum.ROCK -> TypeRock
//            PokemonTypeEnum.GHOST -> TypeGhost
//            PokemonTypeEnum.DRAGON -> TypeDragon
//            PokemonTypeEnum.DARK -> TypeDark
//            PokemonTypeEnum.STEEL -> TypeSteel
//            PokemonTypeEnum.FAIRY -> TypeFairy
//            PokemonTypeEnum.UNKNOWN -> Color.Black
//        }
//    }
}
