package com.almarpa.kmmtemplateapp.core.common.platform

import com.almarpa.kmmtemplateapp.core.common.platform.entities.PlatformData

interface Platform {
    val platformData: PlatformData
}

expect fun getPlatform(): Platform