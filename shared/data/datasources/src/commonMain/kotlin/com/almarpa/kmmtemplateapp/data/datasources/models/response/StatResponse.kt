package com.almarpa.kmmtemplateapp.data.datasources.models.response

import kotlinx.serialization.SerialName

data class StatResponse(
    @SerialName("base_stat")
    val baseStat: Int,
    @SerialName("effort")
    val effort: Int,
    @SerialName("stat")
    val statX: StatXResponse,
)

