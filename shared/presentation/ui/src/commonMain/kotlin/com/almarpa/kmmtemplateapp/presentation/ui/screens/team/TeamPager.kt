package com.almarpa.kmmtemplateapp.presentation.ui.screens.team

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil3.compose.AsyncImage
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.absoluteValue

@Composable
fun TeamPager(pagerState: PagerState, pokemonList: List<Pokemon>) {
    HorizontalPager(
        modifier = Modifier.fillMaxHeight(),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 40.dp),
    ) { page ->
        pokemonList.getOrNull(page % (pokemonList.size))?.let { pokemon ->
            MemberItem(
                pokemon = pokemon,
                pagerState = pagerState,
                page = page
            )
        }
    }
    //}
}

@Composable
fun MemberItem(pokemon: Pokemon, pagerState: PagerState, page: Int) {
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

    Card(
        modifier = Modifier
            .padding(20.dp)
            .height(200.dp + 200.dp * (1 - pageOffset))
            .graphicsLayer {
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            },
        shape = AbsoluteCutCornerShape(40.dp),
        colors = CardDefaults.cardColors(containerColor = Color(pokemon.color))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MemberImage(modifier = Modifier.weight(.6f), pokemon = pokemon)
            MemberName(modifier = Modifier.weight(.2f), pokemon = pokemon)
        }
    }
}

@Composable
fun MemberImage(pokemon: Pokemon, modifier: Modifier) {
    AsyncImage(
        model = pokemon.url,
        contentDescription = "Pokemon Image",
        contentScale = ContentScale.FillBounds,
        modifier = modifier.aspectRatio(1f)
    )
}

@Composable
fun MemberName(pokemon: Pokemon, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 12.dp)
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = pokemon.name.uppercase(),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        )
    }
}

@Composable
@Preview
fun TeamPagerPreview() {
    AppTheme {
        TeamPager(
            pagerState = rememberPagerState { getPokemonListMock().size },
            pokemonList = getPokemonListMock()
        )
    }
}