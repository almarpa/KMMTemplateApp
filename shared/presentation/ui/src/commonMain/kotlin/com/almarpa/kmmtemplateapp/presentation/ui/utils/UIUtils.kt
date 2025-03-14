package com.almarpa.kmmtemplateapp.presentation.ui.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.ui.theme.AtkColor
import com.almarpa.kmmtemplateapp.core.ui.theme.DefColor
import com.almarpa.kmmtemplateapp.core.ui.theme.HPColor
import com.almarpa.kmmtemplateapp.core.ui.theme.SpAtkColor
import com.almarpa.kmmtemplateapp.core.ui.theme.SpDefColor
import com.almarpa.kmmtemplateapp.core.ui.theme.SpdColor
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeBug
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeDark
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeDragon
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeElectric
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeFairy
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeFighting
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeFire
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeFlying
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeGhost
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeGrass
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeGround
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeIce
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeNormal
import com.almarpa.kmmtemplateapp.core.ui.theme.TypePoison
import com.almarpa.kmmtemplateapp.core.ui.theme.TypePsychic
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeRock
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeSteel
import com.almarpa.kmmtemplateapp.core.ui.theme.TypeWater
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

//@Composable
//fun <T> ObserveAsEvents(
//    flow: Flow<T>,
//    key1: Any? = null,
//    key2: Any? = null,
//    onEvent: (T) -> Unit,
//) {
//    val lifecycleOwner = LocalLifecycleOwner.current
//    LaunchedEffect(lifecycleOwner.lifecycle, key1, key2, flow) {
//        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//            withContext(Dispatchers.Main.immediate) {
//                flow.collect(onEvent)
//            }
//        }
//    }
//}

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

@Composable
fun getColorWithGradient(userAppTheme: AppThemeEnum, dominantColor: Int): Brush {
    val color = Color(dominantColor)
    return when (userAppTheme) {
        AppThemeEnum.AUTO -> if (isSystemInDarkTheme()) {
            getDarkGradientByColor(color)
        } else {
            getLightGradientByColor(color)
        }

        AppThemeEnum.DARK -> getDarkGradientByColor(color)
        AppThemeEnum.LIGHT -> getLightGradientByColor(color)
    }
}

private fun getLightGradientByColor(dominantColor: Color) =
    Brush.verticalGradient(
        listOf(Color.White, dominantColor),
        startY = 0.0f,
        endY = 400.0f
    )

private fun getDarkGradientByColor(dominantColor: Color) =
    Brush.verticalGradient(
        listOf(Color.Black, dominantColor),
        startY = 0.0f,
        endY = 400.0f
    )