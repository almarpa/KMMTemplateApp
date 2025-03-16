package com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes

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
                onClick = { onRouteSelected(item.route) }
            )
        }
    }
}