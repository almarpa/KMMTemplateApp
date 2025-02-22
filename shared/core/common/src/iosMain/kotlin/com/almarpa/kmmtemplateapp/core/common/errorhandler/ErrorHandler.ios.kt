package com.almarpa.kmmtemplateapp.core.common.errorhandler

import platform.Foundation.NSError
import platform.Foundation.NSURLErrorDomain

actual fun isNetworkException(e: Throwable): Boolean {
    return (e as? NSError)?.domain == NSURLErrorDomain // TODO: investigate
}