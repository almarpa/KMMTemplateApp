package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleResumeEffect
import coil3.compose.SubcomposeAsyncImage
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.extensions.modifierWithSharedElementTransition
import com.almarpa.kmmtemplateapp.core.ui.composables.dialogs.SimpleActionAlertDialog
import com.almarpa.kmmtemplateapp.core.ui.composables.loader.FullScreenLoader
import com.almarpa.kmmtemplateapp.core.ui.composables.snackbar.CustomSnackBar
import com.almarpa.kmmtemplateapp.core.ui.composables.snackbar.SnackbarController
import com.almarpa.kmmtemplateapp.core.ui.composables.snackbar.SnackbarEvent
import com.almarpa.kmmtemplateapp.core.ui.composables.snackbar.showSnackbar
import com.almarpa.kmmtemplateapp.core.ui.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.core.ui.composables.topappbar.DefaultTopAppBar
import com.almarpa.kmmtemplateapp.core.ui.theme.LocalThemeIsDark
import com.almarpa.kmmtemplateapp.core.ui.utils.BackHandler
import com.almarpa.kmmtemplateapp.core.ui.utils.ObserveAsEvents
import com.almarpa.kmmtemplateapp.core.ui.utils.isTablet
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.presentation.ui.utils.getDarkGradientByColor
import com.almarpa.kmmtemplateapp.presentation.ui.utils.getLightGradientByColor
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.app_error_title
import kmmtemplateapp.shared.presentation.ui.generated.resources.common_accept
import kmmtemplateapp.shared.presentation.ui.generated.resources.empty_string
import kmmtemplateapp.shared.presentation.ui.generated.resources.pokemon_added_to_team
import kmmtemplateapp.shared.presentation.ui.generated.resources.retry_btn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonDetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon,
    pokemonDetailsUiState: PokemonDetailsUiState,
    onFetchDetails: () -> Unit,
    onAddTeamMember: (Pokemon, Boolean) -> Unit,
    onBackPressed: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val snackbarMessage = stringResource(Res.string.pokemon_added_to_team)

    LifecycleResumeEffect(Unit) {
        onFetchDetails()
        onPauseOrDispose {
            // Do nothing
        }
    }

    ObserveAsEvents(
        flow = SnackbarController.snackbarEvents,
        //key1 = snackbarHostState,
    ) { snackbarEvent ->
        coroutineScope.showSnackbar(
            snackbarHostState = snackbarHostState,
            message = snackbarEvent.message,
            actionLabel = snackbarEvent.action?.name,
            onActionPerformed = { snackbarEvent.action?.action?.invoke() },
        )
    }

    BackHandler { onBackPressed() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = { DefaultTopAppBar { onBackPressed() } },
        snackbarHost = { CustomSnackBar(snackbarHostState = snackbarHostState) }
    ) {
        PokemonDetailsContent(
            pokemon = pokemon,
            pokemonDetailsUiState = pokemonDetailsUiState,
            animatedVisibilityScope = animatedVisibilityScope,
            onFetchDetails = { onFetchDetails() },
        ) { pokemon, isAdded ->
            onAddTeamMember(pokemon, isAdded)
            coroutineScope.launch {
                if (isAdded) {
                    SnackbarController.sendSnackbarEvent(event = SnackbarEvent(snackbarMessage))
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.PokemonDetailsContent(
    pokemon: Pokemon,
    pokemonDetailsUiState: PokemonDetailsUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onFetchDetails: () -> Unit,
    onAddTeamMember: (Pokemon, Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                if (LocalThemeIsDark.current) {
                    getDarkGradientByColor(Color(pokemon.color))
                } else {
                    getLightGradientByColor(Color(pokemon.color))
                }
            )
            .statusBarsPadding()
            .systemBarsPadding(),
    ) {
        PokemonCard(
            modifier = Modifier
                .padding(
                    top = 100.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ).fillMaxSize(),
            pokemon = pokemon,
            pokemonDetailsUiState = pokemonDetailsUiState,
            onRetry = { onFetchDetails() }
        )

        PokemonImageAnimation(
            animatedVisibilityScope = animatedVisibilityScope,
            pokemon = pokemon,
            pokemonImageSize = 200.dp
        )

        AddMemberButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 84.dp, start = 32.dp),
            isMemberYet = pokemon.isTeamMember
        ) { isAdded ->
            onAddTeamMember(pokemon, isAdded)
        }
    }
}

@Composable
fun AddMemberButton(
    modifier: Modifier = Modifier,
    isMemberYet: Boolean = false,
    onMemberClick: (Boolean) -> Unit = {},
) {
    Row(modifier = modifier) {
        var isTeamMember by rememberSaveable { mutableStateOf(isMemberYet) }
        val memberIconScale by animateFloatAsState(
            targetValue = if (isTeamMember) 1.1f else 1f,
            label = "Member Button Scale"
        )
        FloatingActionButton(
            modifier = Modifier
                .size(if (isTablet()) 70.dp else 50.dp)
                .aspectRatio(1f)
                .scale(memberIconScale),
            onClick = {
                isTeamMember = !isTeamMember
                onMemberClick(isTeamMember)
            },
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(6.dp),
                imageVector = if (isTeamMember) Icons.Filled.PersonRemove else Icons.Outlined.PersonAdd,
                contentDescription = "Add member icon",
                tint = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    pokemonDetailsUiState: PokemonDetailsUiState,
    onRetry: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .heightIn(min = 600.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (pokemonDetailsUiState) {
            is PokemonDetailsUiState.Loading -> FullScreenLoader()

            is PokemonDetailsUiState.Error -> {
                AppErrorContent(pokemonDetailsUiState, onRetry)
            }

            is PokemonDetailsUiState.Success -> {
                PokemonInfo(
                    pokemon = pokemon,
                    details = pokemonDetailsUiState.details
                )
            }
        }
    }
}

@Composable
fun AppErrorContent(
    pokemonDetailsUiState: PokemonDetailsUiState.Error,
    onRetry: () -> Unit
) {
    var isErrorVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(pokemonDetailsUiState.error) { isErrorVisible = true }

    AppErrorDialog(
        isVisible = isErrorVisible,
        appError = pokemonDetailsUiState.error,
        onAccept = { isErrorVisible = false }
    )
    Button(
        contentPadding = PaddingValues(16.dp),
        onClick = { onRetry() }
    ) {
        Text(stringResource(Res.string.retry_btn))
    }
}

@Composable
fun PokemonInfo(pokemon: Pokemon, details: PokemonDetails) {
    Column {
        PokemonName(
            modifier = Modifier.padding(
                top = if (isTablet()) 160.dp else 120.dp,
            ),
            pokemon = pokemon
        )
        CustomSpacer(height = 16)
        PokemonType(types = details.types)
        PokemonMeasures(
            pokemonWeight = details.weight,
            pokemonHeight = details.height
        )
        PokemonTabRow(
            modifier = Modifier.heightIn(
                max = 300.dp // due to nested scroll need to have a defined height
            ),
            pokemonDetails = details
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonImageAnimation(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon,
    pokemonImageSize: Dp = 200.dp,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(if (isTablet()) 4.dp else 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        SubcomposeAsyncImage(
            model = pokemon.url,
            contentDescription = null,
            modifier = Modifier
                .height(if (isTablet()) pokemonImageSize.plus(50.dp) else pokemonImageSize)
                .aspectRatio(1f)
                .then(
                    modifierWithSharedElementTransition(
                        state = rememberSharedContentState(key = "item-image${pokemon.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                )
        )
    }
}

@Composable
fun AppErrorDialog(isVisible: Boolean, appError: AppError, onAccept: () -> Unit) {
    SimpleActionAlertDialog(
        show = isVisible,
        title = appError.data?.code ?: stringResource(Res.string.app_error_title),
        description = appError.data?.detail ?: stringResource(Res.string.empty_string),
        confirmText = stringResource(Res.string.common_accept),
        onAccept = { onAccept() }
    )
}