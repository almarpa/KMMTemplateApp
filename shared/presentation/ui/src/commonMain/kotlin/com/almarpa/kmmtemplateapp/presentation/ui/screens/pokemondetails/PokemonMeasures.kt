package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.common.model.enums.UnitFormatEnum
import com.almarpa.kmmtemplateapp.core.ui.composables.spacer.CustomSpacer
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.menu_drawer_btn
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round

@Composable
fun PokemonMeasures(
    modifier: Modifier = Modifier,
    pokemonWeight: Int = 100,
    pokemonHeight: Int = 100,
) {
    val pokemonWeightInKg = remember { round(pokemonWeight * 100f) / 1000f }
    val pokemonHeightInMeters = remember { round(pokemonHeight * 100f) / 1000f }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PokemonMeasureItem(
            modifier = Modifier.weight(1f),
            value = pokemonWeightInKg,
            unit = UnitFormatEnum.KG.value,
            icon = Icons.Filled.Scale
        )
        Spacer(
            modifier = Modifier
                .height(60.dp)
                .size(1.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
        PokemonMeasureItem(
            modifier = Modifier.weight(1f),
            value = pokemonHeightInMeters,
            unit = UnitFormatEnum.M.value,
            icon = Icons.Filled.Height,
        )
    }
}

@Composable
fun PokemonMeasureItem(
    modifier: Modifier = Modifier,
    value: Float = 2f,
    unit: String = "Units",
    icon: ImageVector = Icons.Filled.Scale,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .width(40.dp)
                .aspectRatio(1f),
            imageVector = icon,
            contentDescription = stringResource(Res.string.menu_drawer_btn),
            tint = MaterialTheme.colorScheme.primary
        )
        CustomSpacer(height = 4)
        Text(
            text = "$value $unit",
            color = MaterialTheme.colorScheme.primary
        )
    }
}