package com.almarpa.kmmtemplateapp.core.common.errorhandler

import java.io.IOException
import java.net.SocketException
import java.net.UnknownHostException

actual fun isNetworkException(e: Throwable): Boolean {
    return e is UnknownHostException || e is SocketException || e is IOException
}
