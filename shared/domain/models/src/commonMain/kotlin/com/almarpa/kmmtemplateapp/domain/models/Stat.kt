package com.almarpa.kmmtemplateapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    val baseStat: Int,
    val effort: Int,
    val statX: StatX,
)

