package com.almarpa.kmmtemplateapp.domain.models

data class Sprites(
    val backDefault: String?,
    val backShiny: String?,
    val frontDefault: String?,
    val frontShiny: String?,
) {
    constructor() : this(
        backDefault = "",
        backShiny = "",
        frontDefault = "",
        frontShiny = "",
    )
}
