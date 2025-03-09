package com.almarpa.kmmtemplateapp.core.common.platform

interface Platform {
    val platformData: PlatformData
}

expect fun getPlatform(): Platform