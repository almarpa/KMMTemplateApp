package com.almarpa.kmmtemplateapp.data.models.response

import kotlinx.serialization.SerialName

data class SpeciesResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)
