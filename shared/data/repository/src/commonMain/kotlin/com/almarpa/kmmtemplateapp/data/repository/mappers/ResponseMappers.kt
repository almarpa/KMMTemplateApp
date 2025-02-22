package com.almarpa.kmmtemplateapp.data.repository.mappers

import com.almarpa.kmmtemplateapp.data.models.response.FormResponse
import com.almarpa.kmmtemplateapp.data.models.response.MoveResponse
import com.almarpa.kmmtemplateapp.data.models.response.MoveResponseX
import com.almarpa.kmmtemplateapp.data.models.response.PokemonDetailsResponse
import com.almarpa.kmmtemplateapp.data.models.response.PokemonResponse
import com.almarpa.kmmtemplateapp.data.models.response.PokemonResultResponse
import com.almarpa.kmmtemplateapp.data.models.response.SpeciesResponse
import com.almarpa.kmmtemplateapp.data.models.response.SpritesResponse
import com.almarpa.kmmtemplateapp.data.models.response.StatResponse
import com.almarpa.kmmtemplateapp.data.models.response.StatXResponse
import com.almarpa.kmmtemplateapp.data.models.response.TypeResponseX
import com.almarpa.kmmtemplateapp.data.models.response.TypeXXResponse
import com.almarpa.kmmtemplateapp.domain.models.Form
import com.almarpa.kmmtemplateapp.domain.models.Move
import com.almarpa.kmmtemplateapp.domain.models.MoveX
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.domain.models.PokemonResult
import com.almarpa.kmmtemplateapp.domain.models.Species
import com.almarpa.kmmtemplateapp.domain.models.Sprites
import com.almarpa.kmmtemplateapp.domain.models.Stat
import com.almarpa.kmmtemplateapp.domain.models.StatX
import com.almarpa.kmmtemplateapp.domain.models.TypeX
import com.almarpa.kmmtemplateapp.domain.models.TypeXX
import com.almarpa.kmmtemplateapp.domain.models.enums.PokemonTypeEnum
import com.almarpa.kmmtemplateapp.domain.models.enums.StatNameEnum

fun PokemonResultResponse.toDomain(): PokemonResult {
    return PokemonResult(
        count = count,
        next = next,
        previous = previous,
        results = results.map { pokemonResponse -> pokemonResponse.toDomain() },
    )
}

fun PokemonResponse.toDomain(): Pokemon {
    return Pokemon(
        id = url.trimEnd('/').split("/").last().toInt(),
        name = name,
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${
            url.trimEnd('/').split("/").last().toInt()
        }.png",
        createdAt = 0,
        color = 0,
        isTeamMember = false,
    )
}

fun PokemonDetailsResponse.toDomain(): PokemonDetails {
    return PokemonDetails(
        id = id,
        name = name,
        order = order,
        baseExperience = baseExperience,
        height = height,
        weight = weight,
        imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
        stats = stats.map { inner -> inner.toDomain() },
        types = types.map { inner -> inner.toDomain() },
        isDefault = false,
        locationAreaEncounters = "",
        species = species.toDomain(),
        sprites = sprites.toDomain(),
        abilities = listOf(),
        moves = moves.map { inner -> inner.toDomain() },
        forms = forms.map { inner -> inner.toDomain() },
    )
}

fun StatXResponse.toDomain() = StatX(
    name = StatNameEnum.from(name),
    url = url
)

fun MoveResponse.toDomain() = Move(
    move = moveResponseX.toDomain()
)

fun FormResponse.toDomain() = Form(
    name = name,
    url = url,
)

fun MoveResponseX.toDomain() = MoveX(
    name = name,
    url = url
)

fun TypeXXResponse.toDomain() = TypeXX(
    name = PokemonTypeEnum.from(name),
    url = url
)

fun TypeResponseX.toDomain() = TypeX(
    slot = slot,
    typeXX = typeXXResponse.toDomain()
)

fun SpritesResponse.toDomain() = Sprites(
    backDefault = backDefault,
    backFemale = backFemale,
    backShiny = backShiny,
    backShinyFemale = backShinyFemale,
    frontDefault = frontDefault,
    frontFemale = frontFemale,
    frontShiny = frontShiny,
    frontShinyFemale = frontShinyFemale
)

fun SpeciesResponse.toDomain() = Species(
    name = name,
    url = url,
)

fun StatResponse.toDomain() = Stat(
    baseStat = baseStat,
    effort = effort,
    statX = statX.toDomain(),
)