package com.almarpa.kmmtemplateapp.models.features.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entity(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
)