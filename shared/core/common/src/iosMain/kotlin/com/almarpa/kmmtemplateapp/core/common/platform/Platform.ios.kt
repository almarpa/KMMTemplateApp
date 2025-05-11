package com.almarpa.kmmtemplateapp.core.common.platform

import com.almarpa.kmmtemplateapp.core.common.model.enums.PlatformType
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val platformData: PlatformData
        get() = PlatformData(
            platformType = PlatformType.IOS,
            version = UIDevice.currentDevice.systemVersion
        )
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun isIosPlatform(): Boolean = true