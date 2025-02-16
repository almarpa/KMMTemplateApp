package com.almarpa.kmmtemplateapp.domain.models

data class Species(
    val name: String,
    val url: String,
) {
    constructor() : this("", "")
}
