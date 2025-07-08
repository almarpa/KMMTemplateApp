package com.almarpa.kmmtemplateapp.core.presentation.composables.tabrow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.presentation.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.core.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SwipeableTabRow(
    modifier: Modifier,
    tabs: List<String>,
    contentScreens: List<@Composable () -> Unit>,
    containerColor: Color,
    contentColor: Color,
    indicatorColor: Color,
) {
    Column(modifier = modifier.wrapContentSize()) {

        var selectedTabIndex by remember { mutableIntStateOf(0) }
        val pagerState = rememberPagerState { tabs.size }

        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
        }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = containerColor,
            contentColor = contentColor,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = indicatorColor
                )
            }
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(modifier = Modifier.padding(16.dp), text = tabTitle)
                }
            }
        }
        CustomSpacer(height = 16)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            contentScreens.getOrNull(selectedTabIndex)?.invoke()
        }
        CustomSpacer(height = 16)
    }
}

@Composable
@Preview()
fun SwipeableTabRowPreview() {
    AppTheme {
        SwipeableTabRow(
            modifier = Modifier,
            tabs = listOf("Stats", "Moves"),
            contentScreens = listOf(
                { Text("Jetpack") },
                { Text("Compose") },
            ),
            containerColor = Color.White,
            contentColor = Color.Black,
            indicatorColor = Color.Black
        )
    }
}