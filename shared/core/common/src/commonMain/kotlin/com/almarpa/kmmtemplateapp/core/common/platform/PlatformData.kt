package com.almarpa.kmmtemplateapp.core.common.platform

import com.almarpa.kmmtemplateapp.core.common.enums.PlatformType

data class PlatformData(
    val platformType: PlatformType,
    val version: String
) {
    override fun toString(): String = "${platformType.name} v.$version"
}