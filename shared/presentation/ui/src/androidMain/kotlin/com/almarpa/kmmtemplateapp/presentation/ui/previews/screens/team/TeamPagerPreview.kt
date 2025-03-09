package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.team

import android.content.res.Configuration
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.team.TeamPager

@Composable
@Preview("Team Pager", showBackground = true)
@Preview(
    "Team Pager Landscape",
    showBackground = true,
    device = "spec:width=400dp,height=900dp,dpi=420,orientation=landscape"
)
@Preview("Dark Team Pager", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Tablet Dark Team Pager")
fun TeamPagerPreview() {
    KMMTemplateAppTheme {
        TeamPager(
            pagerState = rememberPagerState { getPokemonListMock().size },
            pokemonList = getPokemonListMock()
        )
    }
}