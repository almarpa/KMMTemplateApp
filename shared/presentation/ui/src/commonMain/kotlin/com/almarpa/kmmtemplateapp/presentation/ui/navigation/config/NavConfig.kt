package com.almarpa.kmmtemplateapp.presentation.ui.navigation.config

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.routes.Routes
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val appSavedStateConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Routes.Splash::class, Routes.Splash.serializer())
            subclass(Routes.Main.Home::class, Routes.Main.Home.serializer())
            subclass(Routes.Main.Settings::class, Routes.Main.Settings.serializer())
            subclass(Routes.Main.Detail::class, Routes.Main.Detail.serializer())
        }
    }
}

val homeSavedStateConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Routes.HomeDestination.PokemonList::class, Routes.HomeDestination.PokemonList.serializer())
            subclass(Routes.HomeDestination.Team::class, Routes.HomeDestination.Team.serializer())
        }
    }
}
