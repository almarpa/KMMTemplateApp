package com.almarpa.kmmtemplateapp.data.datasources.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeResponseX(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val typeXXResponse: TypeXXResponse,
)
