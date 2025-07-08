package com.almarpa.kmmtemplateapp.presentation.ui.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.almarpa.kmmtemplateapp.core.presentation.theme.AtkColor
import com.almarpa.kmmtemplateapp.core.presentation.theme.DefColor
import com.almarpa.kmmtemplateapp.core.presentation.theme.HPColor
import com.almarpa.kmmtemplateapp.core.presentation.theme.SpAtkColor
import com.almarpa.kmmtemplateapp.core.presentation.theme.SpDefColor
import com.almarpa.kmmtemplateapp.core.presentation.theme.SpdColor
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeBug
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeDark
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeDragon
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeElectric
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeFairy
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeFighting
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeFire
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeFlying
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeGhost
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeGrass
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeGround
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeIce
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeNormal
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypePoison
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypePsychic
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeRock
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeSteel
import com.almarpa.kmmtemplateapp.core.presentation.theme.TypeWater
import com.almarpa.kmmtemplateapp.domain.models.enums.PokemonTypeEnum
import com.almarpa.kmmtemplateapp.domain.models.enums.StatNameEnum
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.empty_string
import kmmtemplateapp.shared.presentation.ui.generated.resources.stat_attack
import kmmtemplateapp.shared.presentation.ui.generated.resources.stat_defense
import kmmtemplateapp.shared.presentation.ui.generated.resources.stat_hp
import kmmtemplateapp.shared.presentation.ui.generated.resources.stat_special_attack
import kmmtemplateapp.shared.presentation.ui.generated.resources.stat_special_defense
import kmmtemplateapp.shared.presentation.ui.generated.resources.stat_speed
import org.jetbrains.compose.resources.StringResource

fun getAbbreviationByStat(stat: StatNameEnum): StringResource {
    return when (stat) {
        StatNameEnum.HP -> Res.string.stat_hp
        StatNameEnum.ATTACK -> Res.string.stat_attack
        StatNameEnum.DEFENSE -> Res.string.stat_defense
        StatNameEnum.SPECIAL_ATTACK -> Res.string.stat_special_attack
        StatNameEnum.SPECIAL_DEFENSE -> Res.string.stat_special_defense
        StatNameEnum.SPEED -> Res.string.stat_speed
        StatNameEnum.UNKNOWN -> Res.string.empty_string
    }
}

fun getColorByStat(stat: StatNameEnum): Color {
    return when (stat) {
        StatNameEnum.HP -> HPColor
        StatNameEnum.ATTACK -> AtkColor
        StatNameEnum.DEFENSE -> DefColor
        StatNameEnum.SPECIAL_ATTACK -> SpAtkColor
        StatNameEnum.SPECIAL_DEFENSE -> SpDefColor
        StatNameEnum.SPEED -> SpdColor
        StatNameEnum.UNKNOWN -> Color.White
    }
}

fun getColorByType(pokemonType: PokemonTypeEnum): Color {
    return when (pokemonType) {
        PokemonTypeEnum.NORMAL -> TypeNormal
        PokemonTypeEnum.FIRE -> TypeFire
        PokemonTypeEnum.WATER -> TypeWater
        PokemonTypeEnum.ELECTRIC -> TypeElectric
        PokemonTypeEnum.GRASS -> TypeGrass
        PokemonTypeEnum.ICE -> TypeIce
        PokemonTypeEnum.FIGHTING -> TypeFighting
        PokemonTypeEnum.POISON -> TypePoison
        PokemonTypeEnum.GROUND -> TypeGround
        PokemonTypeEnum.FLYING -> TypeFlying
        PokemonTypeEnum.PSYCHIC -> TypePsychic
        PokemonTypeEnum.BUG -> TypeBug
        PokemonTypeEnum.ROCK -> TypeRock
        PokemonTypeEnum.GHOST -> TypeGhost
        PokemonTypeEnum.DRAGON -> TypeDragon
        PokemonTypeEnum.DARK -> TypeDark
        PokemonTypeEnum.STEEL -> TypeSteel
        PokemonTypeEnum.FAIRY -> TypeFairy
        PokemonTypeEnum.UNKNOWN -> Color.Black
    }
}

fun getLightGradientByColor(dominantColor: Color) =
    Brush.verticalGradient(
        listOf(Color.White, dominantColor),
        startY = 0.0f,
        endY = 400.0f
    )

fun getDarkGradientByColor(dominantColor: Color) =
    Brush.verticalGradient(
        listOf(Color.Black, dominantColor),
        startY = 0.0f,
        endY = 400.0f
    )