package com.almarpa.kmmtemplateapp.core.common.platform

import com.almarpa.kmmtemplateapp.core.common.platform.entities.PlatformData
import com.almarpa.kmmtemplateapp.core.common.platform.enums.PlatformType
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val platformData: PlatformData
        get() = PlatformData(
            platformType = PlatformType.IOS,
            osVersion = UIDevice.currentDevice.systemVersion
        )
}

actual fun getPlatform(): Platform = IOSPlatform()