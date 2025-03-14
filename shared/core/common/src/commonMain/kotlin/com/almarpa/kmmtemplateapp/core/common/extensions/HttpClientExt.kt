package com.almarpa.kmmtemplateapp.core.common.extensions

import com.almarpa.kmmtemplateapp.core.common.errorhandler.ErrorHandler
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.model.entities.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> safeCall(call: () -> HttpResponse): Result<T, AppError> {
    return try {
        val response = call()
        if (response.status.isSuccess()) {
            Result.Success(response.body<T>())
        } else {
            Result.Error(ErrorHandler.processErrorResponse(response))
        }
    } catch (e: Exception) {
        Result.Error(ErrorHandler.processException(e))
    }
}
