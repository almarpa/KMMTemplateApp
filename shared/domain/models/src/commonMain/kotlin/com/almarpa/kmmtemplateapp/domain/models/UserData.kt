package com.almarpa.kmmtemplateapp.domain.models

import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum

data class UserData(
    val locale: String,
    val theme: AppThemeEnum,
)
