package com.almarpa.kmmtemplateapp.data.repository.mappers

import com.almarpa.kmmtemplateapp.data.datasources.models.entities.PokemonDetailsEntity
import com.almarpa.kmmtemplateapp.data.datasources.models.entities.PokemonEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails

fun Pokemon.toEntity() = PokemonEntity(
    id = id,
    url = url,
    name = name,
    createdAt = createdAt,
    color = color,
    isTeamMember = isTeamMember,
)

fun PokemonDetails.toEntity(): PokemonDetailsEntity =
    PokemonDetailsEntity(
        id,
        name,
        order,
        baseExperience,
        height,
        weight,
        imageURL,
        stats,
        types,
        moves,
        isDefault,
        locationAreaEncounters,
        species,
        sprites,
        abilities,
        forms
    )