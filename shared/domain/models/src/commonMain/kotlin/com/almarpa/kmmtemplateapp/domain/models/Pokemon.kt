package com.almarpa.kmmtemplateapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val url: String,
    val name: String,
    val createdAt: Long = 0L,
    var color: Int = 0,
    var isTeamMember: Boolean = false,
) {

    constructor(
        url: String,
        name: String,
        color: Int,
    ) : this(
        id = 0,
        url = url,
        name = name,
        createdAt = 0L,
        color = color,
        isTeamMember = true,
    )
}