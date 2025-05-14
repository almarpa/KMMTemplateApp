package com.almarpa.kmmtemplateapp.core.common.errorhandler.entities

import com.almarpa.kmmtemplateapp.core.common.extensions.whenAllNotNull

/**
 * Represents the error information for an [AppError]
 *
 * @property code HTTP error code or null
 * @property detail error description
 */
data class AppErrorData(
    val code: String? = null,
    val detail: String? = null,
) {

    fun getFormatted() = whenAllNotNull(code, detail) { codeNotNull, detailNotNull ->
        "$codeNotNull: $detailNotNull"
    } ?: ""
}