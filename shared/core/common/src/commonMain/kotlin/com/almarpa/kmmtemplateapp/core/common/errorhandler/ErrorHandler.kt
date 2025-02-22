package com.almarpa.kmmtemplateapp.core.common.errorhandler


import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppErrorData
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppErrorType
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import kotlinx.io.IOException

object ErrorHandler {

    fun processErrorResponse(response: HttpResponse): AppError {
        return AppError(
            type = getAppErrorType(response.status.value),
            data = AppErrorData(
                code = response.status.value.toString(),
                detail = "" // TODO: response.string()
            )
        )
    }

    fun processException(exception: Throwable): AppError =
        when (exception) {
            is AppError -> exception
            is Exception -> getAppError(exception)
            else -> getDefaultAppError(exception)
        }

    private fun getAppErrorType(httpErrorCode: Int): AppErrorType =
        when (httpErrorCode) {
            in 200..299 -> AppErrorType.MalformedResponse
            in 401..403 -> AppErrorType.Api.Forbidden
            404 -> AppErrorType.Api.NotFound
            in 400..499 -> AppErrorType.Api.MalformedRequest
            in 500..599 -> AppErrorType.Api.InternalServerError
            else -> AppErrorType.Unknown
        }

    private fun getAppError(exception: Throwable): AppError =
        when (exception) {
            is HttpRequestTimeoutException, is ConnectTimeoutException, is SocketTimeoutException -> {
                getTimeoutException(exception)
            }

            is IOException -> {
                processIOException(exception)
            }

            else -> {
                getDefaultAppError(exception)
            }
        }

    private fun getTimeoutException(exception: Throwable) = AppError(
        type = AppErrorType.Api.Timeout,
        data = AppErrorData(detail = "Timeout exception"),
        cause = exception
    )

    private fun processIOException(ioException: IOException): AppError =
        when {
            isNetworkException(ioException) -> {
                AppError(
                    type = AppErrorType.Api.Offline,
                    data = AppErrorData(detail = "No Internet connection"),
                    cause = ioException
                )
            }

            else -> getDefaultAppError(ioException)
        }

    private fun getDefaultAppError(exception: Throwable) =
        AppError(
            type = AppErrorType.MalformedResponse,
            data = AppErrorData(detail = "Malformed response"),
            cause = exception
        )
}

expect fun isNetworkException(e: Throwable): Boolean
