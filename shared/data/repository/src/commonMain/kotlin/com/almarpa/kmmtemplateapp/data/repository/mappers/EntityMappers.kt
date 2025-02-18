package com.almarpa.kmmtemplateapp.data.repository.mappers

import com.almarpa.kmmtemplateapp.data.models.entities.PokemonDetailsEntity
import com.almarpa.kmmtemplateapp.data.models.entities.PokemonEntity
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails

fun PokemonEntity.toDomain() = Pokemon(
    id = id,
    url = url,
    name = name,
    createdAt = createdAt,
    color = color,
    isTeamMember = isTeamMember,
)

fun PokemonDetailsEntity.toDomain(): PokemonDetails = PokemonDetails(
    id = id,
    name = name,
    order = order,
    baseExperience = baseExperience,
    height = height,
    weight = weight,
    imageURL = imageURL,
    stats = stats,
    types = types,
    moves = moves,
    isDefault = isDefault,
    locationAreaEncounters = locationAreaEncounters,
    species = species,
    sprites = sprites,
    abilities = abilities,
    forms = forms,
)
