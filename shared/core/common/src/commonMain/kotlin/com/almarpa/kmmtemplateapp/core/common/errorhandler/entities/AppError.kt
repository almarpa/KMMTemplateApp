package com.almarpa.kmmtemplateapp.core.common.errorhandler.entities

/**
 * It represents an Exception in the App
 * @property type The exception type
 * @property data the exception data
 * @param cause the cause of the error
 */
class AppError(
    val type: AppErrorType = AppErrorType.Unknown,
    val data: AppErrorData? = null,
    override val cause: Throwable? = null,
) : Exception(data?.getFormatted(), cause)