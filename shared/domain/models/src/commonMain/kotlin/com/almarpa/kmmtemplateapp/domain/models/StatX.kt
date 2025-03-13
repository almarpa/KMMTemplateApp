package com.almarpa.kmmtemplateapp.domain.models

import com.almarpa.kmmtemplateapp.domain.models.enums.StatNameEnum
import kotlinx.serialization.Serializable

@Serializable
data class StatX(
    val name: StatNameEnum,
    val url: String,
)
