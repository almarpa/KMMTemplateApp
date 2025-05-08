package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almarpa.kmmtemplateapp.core.ui.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.domain.models.Stat
import com.almarpa.kmmtemplateapp.domain.models.StatX
import com.almarpa.kmmtemplateapp.domain.models.enums.StatNameEnum
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonStatListMock
import com.almarpa.kmmtemplateapp.presentation.ui.utils.getAbbreviationByStat
import com.almarpa.kmmtemplateapp.presentation.ui.utils.getColorByStat
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PokemonStats(stats: List<Stat>) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        stats.forEach { stat ->
            PokemonStat(stat = stat)
            CustomSpacer(height = 4)
        }
    }
}

@Composable
fun PokemonStat(stat: Stat) {
    var startAnim by rememberSaveable { mutableStateOf(false) }
    val animatedStatValue by animateFloatAsState(
        targetValue = if (startAnim) stat.baseStat / 100.toFloat() else .15f,
        animationSpec = tween(1000, 100),
        label = "StatProgressAnimation"
    )

    LaunchedEffect(Unit) { startAnim = true }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(.2f),
            text = stringResource(getAbbreviationByStat(stat.statX.name)),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurface),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedStatValue)
                    .clip(CircleShape)
                    .background(getColorByStat(stat.statX.name))
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = (animatedStatValue * 100).toInt().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp
                )
            }
        }
    }
}


@Preview
@Composable
fun PokemonStatsPreview() {
    PokemonStats(getPokemonStatListMock())
}

@Preview
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