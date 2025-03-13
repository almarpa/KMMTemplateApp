package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.detail

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.domain.models.Stat
import com.almarpa.kmmtemplateapp.domain.models.StatX
import com.almarpa.kmmtemplateapp.domain.models.enums.StatNameEnum
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonStatListMock
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonStat
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails.PokemonStats

@Preview("Pokemon Stats", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PokemonStatsPreview() {
    PokemonStats(getPokemonStatListMock())
}

@Preview("Pokemon Stat")
@Composable
fun PokemonStatPreview() {
    PokemonStat(
        Stat(
            baseStat = 50,
            effort = 50,
            statX = StatX(name = StatNameEnum.HP, url = "")
        )
    )
}