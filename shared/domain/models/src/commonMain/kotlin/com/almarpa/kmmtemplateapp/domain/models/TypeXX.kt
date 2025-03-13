package com.almarpa.kmmtemplateapp.domain.models

import com.almarpa.kmmtemplateapp.domain.models.enums.PokemonTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class TypeXX(
    val name: PokemonTypeEnum,
    val url: String,
)
