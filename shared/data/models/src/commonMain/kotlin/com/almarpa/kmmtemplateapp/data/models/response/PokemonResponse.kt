package com.almarpa.kmmtemplateapp.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PokemonResponse(
    @SerialName("name")
    var name: String,
    @SerialName("url")
    var url: String,
)
