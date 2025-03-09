package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.team

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.screens.team.AnimatedFabContainer

@Composable
@Preview("Animated Fab Container", showBackground = true)
@Preview(
    "Dark Animated Fab Container",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun AddPokemonFloatingButtonPreview() {
    KMMTemplateAppTheme {
        AnimatedFabContainer(
            fabContainerState = false,
            onFabContainerStateChanged = {},
            onSave = {}
        )
    }
}

@Composable
@Preview("Fab Container Fullscreen", showBackground = true)
@Preview(
    "Dark Fab Container Fullscreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    "Fab Container Fullscreen Landscape",
    showBackground = true,
    device = "spec:width=400dp,height=900dp,dpi=420,orientation=landscape"
)
@Preview(name = "Tablet Fab Container Fullscreen", showBackground = true)
@Preview(
    "Dark Tablet Fab Container Fullscreen",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
fun FabContainerFullscreenPreview() {
    KMMTemplateAppTheme {
        AnimatedFabContainer(
            fabContainerState = true,
            onFabContainerStateChanged = {},
            onSave = {}
        )
    }
}