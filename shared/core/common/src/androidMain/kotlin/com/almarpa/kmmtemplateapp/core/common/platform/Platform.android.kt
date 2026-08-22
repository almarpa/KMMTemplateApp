package com.almarpa.kmmtemplateapp.core.common.platform

import android.os.Build
import com.almarpa.kmmtemplateapp.core.common.model.enums.PlatformType
import java.util.Locale

class AndroidPlatform : Platform {
    override val platformData: PlatformData
        get() = PlatformData(
            platformType = PlatformType.ANDROID,
            version = Build.VERSION.SDK_INT.toString()
        )

    override val deviceLocale: String
        get() = Locale.getDefault().language
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun isIosPlatform(): Boolean = false