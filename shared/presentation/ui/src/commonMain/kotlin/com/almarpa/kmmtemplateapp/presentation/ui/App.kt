package com.almarpa.kmmtemplateapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.almarpa.kmmtemplateapp.core.common.platform.getPlatform
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonViewModel
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun App() {
    MaterialTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val viewModel = koinViewModel<PokemonViewModel>()
                val pokemonCount = viewModel.uiState.collectAsStateWithLifecycle()

                val platform = getPlatform()
                val appName = stringResource(Res.string.app_name)

                Text(appName)
                Text("${platform.platformData}")
                Text("Pokemons: ${pokemonCount.value}")
            }
        }
    }
}