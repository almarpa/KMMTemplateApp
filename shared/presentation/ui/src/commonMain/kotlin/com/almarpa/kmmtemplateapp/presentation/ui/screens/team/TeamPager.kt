package com.almarpa.kmmtemplateapp.presentation.ui.screens.team

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.almarpa.kmmtemplateapp.core.presentation.theme.AppTheme
import com.almarpa.kmmtemplateapp.core.presentation.theme.DarkSilver
import com.almarpa.kmmtemplateapp.core.presentation.theme.Platinum
import com.almarpa.kmmtemplateapp.core.presentation.theme.Silver
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
}

@Composable
fun MemberItem(pokemon: Pokemon, pagerState: PagerState, page: Int) {
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

    val containerColor = Color(pokemon.color)
    val isDark = isSystemInDarkTheme()

    Card(
        modifier = Modifier
            .padding(20.dp)
            .height(250.dp + 200.dp * (1 - pageOffset))
            .shadow(
                elevation = 30.dp,
                shape = RoundedCornerShape(32.dp),
                spotColor = if (isDark) containerColor.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.2f),
                ambientColor = Color.Black.copy(alpha = 0.5f)
            )
            .border(
                width = 6.dp,
                brush = Brush.linearGradient(
                    colors = if (isDark) {
                        listOf(Platinum, Color.White, Silver, DarkSilver, Color.White, Platinum)
                    } else {
                        listOf(Silver, Platinum, Color.White, Silver, DarkSilver, Silver)
                    },
                    start = Offset.Zero,
                    end = Offset(500f, 500f)
                ),
                shape = RoundedCornerShape(32.dp)
            )
            .border(
                width = 1.dp,
                color = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.15f),
                shape = RoundedCornerShape(32.dp)
            ),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MemberCardDecorations()

            MemberContent(
                pokemon = pokemon,
                pageOffset = pageOffset
            )
        }
    }
}

@Composable
private fun MemberCardDecorations() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.4f),
                        Color.Transparent
                    ),
                    center = Offset(0f, 0f),
                    radius = 800f
                )
            )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.2f)
                    )
                )
            )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    0.0f to Color.White.copy(alpha = 0.1f),
                    0.4f to Color.Transparent,
                    0.6f to Color.Transparent,
                    1.0f to Color.White.copy(alpha = 0.05f),
                    start = Offset.Zero,
                    end = Offset(1000f, 1000f)
                )
            )
    )
}

@Composable
private fun MemberContent(
    pokemon: Pokemon,
    pageOffset: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "#${pokemon.id.toString().padStart(3, '0')}",
            modifier = Modifier.align(Alignment.Start),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White.copy(alpha = 0.8f),
            fontWeight = FontWeight.ExtraBold
        )

        MemberImage(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            pokemon = pokemon
        )

        MemberName(
            modifier = Modifier,
            pokemon = pokemon
        )
    }
}

@Composable
fun MemberImage(pokemon: Pokemon, modifier: Modifier) {
    AsyncImage(
        model = pokemon.url,
        contentDescription = "Pokemon Image",
        contentScale = ContentScale.Fit,
        modifier = modifier.aspectRatio(1f)
    )
}

@Composable
fun MemberName(pokemon: Pokemon, modifier: Modifier) {
    Text(
        modifier = modifier.padding(bottom = 8.dp),
        text = pokemon.name.uppercase(),
        color = Color.White,
        style = MaterialTheme.typography.headlineSmall.copy(
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.3f),
                offset = Offset(4f, 4f),
                blurRadius = 8f
            )
        ),
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
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
