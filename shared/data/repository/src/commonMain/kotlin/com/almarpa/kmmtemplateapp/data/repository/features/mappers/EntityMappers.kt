package com.almarpa.kmmtemplateapp.data.repository.features.mappers

import com.almarpa.kmmtemplateapp.data.models.entities.PokemonEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon

fun PokemonEntity.toDomain() = Pokemon(
    id = id,
    url = url,
    name = name,
    createdAt = createdAt,
    color = color,
    isTeamMember = isTeamMember,
)