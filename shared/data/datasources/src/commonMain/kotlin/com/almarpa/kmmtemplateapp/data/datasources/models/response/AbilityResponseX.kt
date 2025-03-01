package com.almarpa.kmmtemplateapp.data.datasources.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityResponseX(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)
