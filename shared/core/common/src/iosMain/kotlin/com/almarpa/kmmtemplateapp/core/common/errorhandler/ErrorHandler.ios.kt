package com.almarpa.kmmtemplateapp.core.common.errorhandler

import platform.Foundation.NSError
import platform.Foundation.NSURLErrorDomain
import platform.Foundation.NSURLErrorNotConnectedToInternet
import platform.Foundation.NSURLErrorTimedOut

actual fun isNetworkException(e: Throwable): Boolean {
    val nsError = (e as? NSError) ?: (e.cause as? NSError) ?: return false
    return (nsError.code == NSURLErrorNotConnectedToInternet || nsError.code == NSURLErrorTimedOut)
            && nsError.domain == NSURLErrorDomain
}