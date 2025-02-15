package com.almarpa.kmmtemplateapp.domain.models

import com.almarpa.kmmtemplateapp.domain.models.enums.AppThemeEnum

data class UserData(
    val locale: String,
    val theme: AppThemeEnum,
)
