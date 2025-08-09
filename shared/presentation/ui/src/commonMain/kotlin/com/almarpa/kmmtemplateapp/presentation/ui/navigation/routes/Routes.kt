package com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes

import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import kotlinx.serialization.Serializable

/**
 * Destinations used throughout the app.
 */
sealed interface Routes {

    @Serializable
    data object Splash : Routes

    @Serializable
    data object PokemonList : Routes

    @Serializable
    data object Team : Routes

    @Serializable
    data object Settings : Routes

    @Serializable
    data class Detail(val pokemon: Pokemon) : Routes
}
