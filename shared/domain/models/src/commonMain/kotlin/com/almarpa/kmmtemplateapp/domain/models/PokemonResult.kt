package com.almarpa.kmmtemplateapp.domain.models

data class PokemonResult(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: List<Pokemon>,
)
