package com.almarpa.kmmtemplateapp.core.common.platform

import android.os.Build
import com.almarpa.kmmtemplateapp.core.common.model.enums.PlatformType

class AndroidPlatform : Platform {
    override val platformData: PlatformData
        get() = PlatformData(
            platformType = PlatformType.ANDROID,
            version = Build.VERSION.SDK_INT.toString()
        )
}

actual fun getPlatform(): Platform = AndroidPlatform()