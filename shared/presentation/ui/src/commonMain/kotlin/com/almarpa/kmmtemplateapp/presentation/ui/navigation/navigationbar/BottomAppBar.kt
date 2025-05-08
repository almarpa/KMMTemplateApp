package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import org.jetbrains.compose.ui.tooling.preview.Preview

data class BottomAppBarItem(
    val icon: @Composable () -> Unit,
    val label: String,
    val color: Color,
    val route: Routes,
)


@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    bottomAppBarItems: List<BottomAppBarItem>,
    currentRoute: Routes,
    onRouteSelected: (Routes) -> Unit = {},
) {
    NavigationBar(
        modifier = modifier.clip(RoundedCornerShape(20.dp)),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        bottomAppBarItems.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { item.label },
                selected = currentRoute == item.route,
                onClick = { onRouteSelected(item.route) },
            )
        }
    }
}

@Composable
@Preview()
fun TemplateBottomAppBarPreview() {
    AppTheme {
        BottomAppBar(
            currentRoute = Routes.PokemonList,
            bottomAppBarItems = listOf(
                BottomAppBarItem(
                    icon = {
                        Icon(
                            Icons.AutoMirrored.Outlined.List,
                            contentDescription = "Pokedex",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    },
                    label = "Pokedex",
                    color = MaterialTheme.colorScheme.primary,
                    route = Routes.PokemonList,
                ),
                BottomAppBarItem(
                    icon = {
                        Icon(
                            Icons.Outlined.Person,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Team",
                        )
                    },
                    label = "Team",
                    color = MaterialTheme.colorScheme.primary,
                    route = Routes.Team,
                )
            ),
            onRouteSelected = {},
        )
    }
}