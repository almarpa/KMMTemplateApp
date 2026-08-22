package com.almarpa.kmmtemplateapp.core.common.platform

interface Platform {
    val platformData: PlatformData
    val deviceLocale: String
}

expect fun getPlatform(): Platform

expect fun isIosPlatform(): Boolean