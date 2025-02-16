package com.almarpa.kmmtemplateapp.data.repository.features.mappers

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

fun PokemonResultResponse.map(): PokemonResult {
    return PokemonResult(
        count = count,
        next = next,
        previous = previous,
        results = results.map { pokemonResponse -> pokemonResponse.map() },
    )
}

fun PokemonResponse.map(): Pokemon {
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

fun PokemonDetailsResponse.map(): PokemonDetails {
    return PokemonDetails(
        id = id,
        name = name,
        order = order,
        baseExperience = baseExperience,
        height = height,
        weight = weight,
        imageURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
        stats = stats.map { inner -> inner.map() },
        types = types.map { inner -> inner.map() },
        isDefault = false,
        locationAreaEncounters = "",
        species = species.map(),
        sprites = sprites.map(),
        abilities = listOf(),
        moves = moves.map { inner -> inner.map() },
        forms = forms.map { inner -> inner.map() },
    )
}

fun StatXResponse.map() = StatX(
    StatNameEnum.from(name),
    url
)

fun MoveResponse.map() = Move(
    moveResponseX.map(),
)

fun FormResponse.map() = Form(
    name = name,
    url = url,
)

fun MoveResponseX.map() = MoveX(
    name,
    url
)

fun TypeXXResponse.map() = TypeXX(
    PokemonTypeEnum.from(name),
    url
)

fun TypeResponseX.map() = TypeX(
    slot,
    typeXXResponse.map()
)

fun SpritesResponse.map() = Sprites(
    backDefault,
    backFemale,
    backShiny,
    backShinyFemale,
    frontDefault,
    frontFemale,
    frontShiny,
    frontShinyFemale
)

fun SpeciesResponse.map() = Species(
    name = name,
    url = url,
)

fun StatResponse.map() = Stat(
    baseStat,
    effort,
    statX.map(),
)