package com.almarpa.kmmtemplateapp.data.models.response

import kotlinx.serialization.SerialName

data class StatXResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)
