package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.almarpa.kmmtemplateapp.core.ui.composables.error.ErrorPlaceholderView
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.error_getting_pokemon_list
import kmmtemplateapp.shared.presentation.ui.generated.resources.retry_btn
import org.jetbrains.compose.resources.stringResource

@PreviewLightDark
@Composable
fun ErrorPlaceholderViewWithActionPreview() {
    ErrorPlaceholderView(
        errorDescription = stringResource(Res.string.error_getting_pokemon_list),
        actionText = stringResource(Res.string.retry_btn)
    )
}

@PreviewLightDark
@Composable
fun ErrorPlaceholderViewPreview() {
    ErrorPlaceholderView(
        errorDescription = stringResource(Res.string.error_getting_pokemon_list),
        actionText = null
    )
}
