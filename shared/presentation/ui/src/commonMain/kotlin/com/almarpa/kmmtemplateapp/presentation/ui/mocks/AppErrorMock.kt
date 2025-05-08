package com.almarpa.kmmtemplateapp.presentation.ui.mocks

import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppErrorData
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppErrorType

fun mockNotFoundAppError() = AppError(
    type = AppErrorType.Api.NotFound,
    data = mockAppErrorData(),
    cause = null
)

fun mockAppErrorData() = AppErrorData(
    "404",
    "Not Found"
)
