package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almarpa.kmmtemplateapp.core.ui.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.domain.models.TypeX
import com.almarpa.kmmtemplateapp.domain.models.TypeXX
import com.almarpa.kmmtemplateapp.domain.models.enums.PokemonTypeEnum
import com.almarpa.kmmtemplateapp.presentation.ui.utils.getColorByType
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PokemonType(modifier: Modifier = Modifier, types: List<TypeX>) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (type in types) {
            CustomSpacer(width = 8)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(getColorByType(type.typeXX.name))
                    .height(30.dp)
            ) {
                Text(
                    text = type.typeXX.name.value.uppercase(), color = Color.White, fontSize = 18.sp
                )
            }
            CustomSpacer(width = 8)
        }
    }
}

@Composable
@Preview
fun PokemonTypePreview() {
    PokemonType(
        types = listOf(
            TypeX(1, TypeXX(PokemonTypeEnum.ICE, "")),
            TypeX(2, TypeXX(PokemonTypeEnum.PSYCHIC, "")),
        )
    )
}