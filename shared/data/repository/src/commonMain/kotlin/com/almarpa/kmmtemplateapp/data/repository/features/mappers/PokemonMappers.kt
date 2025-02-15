package com.almarpa.kmmtemplateapp.data.repository.features.mappers

import com.almarpa.kmmtemplateapp.data.datasources.core.local.db.entities.PokemonEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon

fun PokemonEntity.asDomain() = Pokemon(
    id = id,
    url = url,
    name = name,
    createdAt = createdAt,
    color = color,
    isTeamMember = isTeamMember,
)

fun Pokemon.asEntity() = PokemonEntity(
    id = id,
    url = url,
    name = name,
    createdAt = createdAt,
    color = color,
    isTeamMember = isTeamMember,
)