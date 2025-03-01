package com.almarpa.kmmtemplateapp.data.datasources.models.response

import kotlinx.serialization.SerialName

data class FormResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)
