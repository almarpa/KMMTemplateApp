package com.almarpa.kmmtemplateapp.core.common.model.entities

import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError

sealed interface Result<out D, out AppError> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error(val error: AppError) : Result<Nothing, AppError>
}

inline fun <T, R> Result<T, AppError>.map(map: (T) -> R): Result<R, AppError> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

inline fun <T, AppError> Result<T, AppError>.onSuccess(action: (T) -> Unit): Result<T, AppError> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T> Result<T, AppError>.onError(action: (AppError) -> Unit): Result<T, AppError> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}

typealias EmptyResult<E> = Result<Unit, E>