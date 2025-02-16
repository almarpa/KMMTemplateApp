package com.almarpa.kmmtemplateapp.data.models.response

import kotlinx.serialization.SerialName

data class PokemonDetailsResponse(
    @SerialName("abilities")
    val abilities: List<AbilityResponse>,
    @SerialName("base_experience")
    val baseExperience: Int,
    @SerialName("forms")
    val forms: List<FormResponse>,
    @SerialName("height")
    val height: Int,
    @SerialName("held_items")
    val heldItems: List<Any>,
    @SerialName("id")
    val id: Int,
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerialName("moves")
    val moves: List<MoveResponse>,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("species")
    val species: SpeciesResponse,
    @SerialName("sprites")
    val sprites: SpritesResponse,
    @SerialName("stats")
    val stats: List<StatResponse>,
    @SerialName("types")
    val types: List<TypeResponseX>,
    @SerialName("weight")
    val weight: Int,
)
