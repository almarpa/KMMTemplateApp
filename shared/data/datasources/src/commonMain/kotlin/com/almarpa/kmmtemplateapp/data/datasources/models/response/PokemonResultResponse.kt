package com.almarpa.kmmtemplateapp.data.datasources.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResultResponse(
    @SerialName("count")
    var count: Int,
    @SerialName("next")
    var next: String,
    @SerialName("previous")
    var previous: String?,
    @SerialName("results")
    var results: List<PokemonResponse>,
)
