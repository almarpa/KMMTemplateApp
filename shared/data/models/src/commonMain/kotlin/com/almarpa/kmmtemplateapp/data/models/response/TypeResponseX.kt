package com.almarpa.kmmtemplateapp.data.models.response

import kotlinx.serialization.SerialName

data class TypeResponseX(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val typeXXResponse: TypeXXResponse,
)
