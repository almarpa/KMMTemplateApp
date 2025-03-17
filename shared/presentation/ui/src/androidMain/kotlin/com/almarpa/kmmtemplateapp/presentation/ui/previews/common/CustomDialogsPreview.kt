package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.composables.dialogs.CustomDialog
import com.almarpa.kmmtemplateapp.core.ui.composables.dialogs.SimpleActionAlertDialog
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.common_accept
import kmmtemplateapp.shared.presentation.ui.generated.resources.common_cancel
import kmmtemplateapp.shared.presentation.ui.generated.resources.error_getting_pokemon_list
import kmmtemplateapp.shared.presentation.ui.generated.resources.search_title
import org.jetbrains.compose.resources.stringResource

@Preview
@Composable
fun SimpleAlertDialogPreview() {
    SimpleActionAlertDialog(
        show = true,
        title = stringResource(Res.string.search_title),
        description = stringResource(Res.string.error_getting_pokemon_list),
        confirmText = stringResource(Res.string.common_accept)
    )
}

@Preview
@Composable
fun CustomDialogPreview() {
    CustomDialog(
        title = stringResource(Res.string.search_title),
        description = stringResource(Res.string.error_getting_pokemon_list),
        confirmText = stringResource(Res.string.common_accept),
        cancelText = stringResource(Res.string.common_cancel)
    )
}