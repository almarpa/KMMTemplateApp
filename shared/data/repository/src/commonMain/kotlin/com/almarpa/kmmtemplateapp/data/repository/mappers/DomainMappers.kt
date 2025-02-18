package com.almarpa.kmmtemplateapp.data.repository.mappers

import com.almarpa.kmmtemplateapp.data.models.entities.PokemonEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon

fun Pokemon.toEntity() = PokemonEntity(
    id = id,
    url = url,
    name = name,
    createdAt = createdAt,
    color = color,
    isTeamMember = isTeamMember,
)