package com.almarpa.kmmtemplateapp.core.common.model.enums

enum class LocaleEnum(val value: String) {
    EN("en"),
    ES("es"),
    DE("de"),
    PT("pt"),
    FR("fr"),
    ZH("zh"),
    IT("it");

    companion object {
        fun fromString(locale: String?): LocaleEnum {
            val normalized = locale?.takeIf { it.isNotBlank() }
                ?.substringBefore("-")
                ?.substringBefore("_")
                ?.lowercase()

            return entries.find { it.value == normalized } ?: EN
        }
    }
}
