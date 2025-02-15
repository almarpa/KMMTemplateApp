package com.almarpa.kmmtemplateapp.domain.models.enums

enum class StatNameEnum(val value: String) {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed"),
    UNKNOWN("unknown");

    companion object {
        fun from(name: String) =
            entries.find { statNameEnum -> statNameEnum.value == name } ?: UNKNOWN
    }
}