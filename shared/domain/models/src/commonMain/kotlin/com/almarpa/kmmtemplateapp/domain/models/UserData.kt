package com.almarpa.kmmtemplateapp.domain.models

import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.common.model.enums.LocaleEnum

data class UserData(
    val locale: LocaleEnum,
    val theme: AppThemeEnum,
)
