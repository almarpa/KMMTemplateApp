package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.composables.dropdown.CustomDropdown


@Preview(name = "Custom Dropdown")
@Preview(name = "Dark Custom Dropdown", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomDropdownPreview() {
    CustomDropdown(
        items = mapOf(),
        selected = "Test",

    )
}