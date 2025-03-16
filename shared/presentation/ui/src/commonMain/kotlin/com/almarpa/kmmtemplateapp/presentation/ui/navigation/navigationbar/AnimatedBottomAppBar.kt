package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ManageSearch
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.team_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedBottomAppBar(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    currentRoute: Routes,
    onRouteSelected: (Routes) -> Unit,
) {
    val bottomAppBarItems = listOf(
        BottomAppBarItem(
            icon = {
                Icon(
                    Icons.AutoMirrored.Outlined.ManageSearch,
                    contentDescription = "Pokedex",
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            },
            label = stringResource(Res.string.team_title),
            color = MaterialTheme.colorScheme.tertiary,
            route = Routes.PokemonList,
        ),
        BottomAppBarItem(
            icon = {
                Icon(
                    if (currentRoute == Routes.Team) {
                        Icons.Outlined.PeopleOutline
                    } else {
                        Icons.Default.PeopleOutline
                    },
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = "Team",
                )
            },
            label = stringResource(Res.string.team_title),
            color = MaterialTheme.colorScheme.tertiary,
            route = Routes.Team,
        ),
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing
            )
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing
            )
        )
    ) {
        BottomAppBar(
            modifier = modifier,
            bottomAppBarItems = bottomAppBarItems,
            currentRoute = currentRoute,
            onRouteSelected = { newRoute -> onRouteSelected(newRoute) },
        )
    }
}