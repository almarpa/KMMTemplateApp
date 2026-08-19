package com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes

import androidx.navigation3.runtime.NavKey
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import kotlinx.serialization.Serializable

/**
 * Destinations used throughout the app.
 */
@Serializable
sealed interface Routes : NavKey {

    @Serializable
    data object Splash : Routes

    @Serializable
    sealed interface Main : Routes {
        @Serializable
        data object Home : Main

        @Serializable
        data class Detail(val pokemon: Pokemon) : Main

        @Serializable
        data object Settings : Main
    }

    @Serializable
    sealed interface HomeDestination : Routes {
        @Serializable
        data object PokemonList : HomeDestination

        @Serializable
        data object Team : HomeDestination
    }
}
