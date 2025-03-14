package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemondetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleResumeEffect
import coil3.compose.SubcomposeAsyncImage
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.extensions.applyIfNotCurrentLocalInspectionMode
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.ui.composables.loader.FullScreenLoader
import com.almarpa.kmmtemplateapp.core.ui.composables.spacer.CustomSpacer
import com.almarpa.kmmtemplateapp.core.ui.composables.topappbar.DefaultTopAppBar
import com.almarpa.kmmtemplateapp.core.ui.utils.isTablet
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.presentation.ui.utils.getColorWithGradient
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonDetailsUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.retry_btn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonDetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon,
    pokemonDetailsUiState: PokemonDetailsUiState,
    userAppTheme: AppThemeEnum,
    onFetchDetails: () -> Unit,
    onAddTeamMember: (Pokemon, Boolean) -> Unit,
    onBackPressed: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    //val snackbarMessage = LocalContext.current.getString(R.string.pokemon_added_to_team)

    LifecycleResumeEffect(Unit) {
        onFetchDetails()
        onPauseOrDispose {
            // Do nothing
        }
    }

//    ObserveAsEvents(
//        flow = SnackbarController.snackbarEvents,
//        key1 = snackbarHostState,
//    ) { snackbarEvent ->
//        coroutineScope.showSnackbar(
//            snackbarHostState = snackbarHostState,
//            message = snackbarEvent.message,
//            actionLabel = snackbarEvent.action?.name,
//            onActionPerformed = { snackbarEvent.action?.action?.invoke() },
//        )
//    }
//
//    BackHandler { onBackPressed() }

    Scaffold(
        topBar = { DefaultTopAppBar { onBackPressed() } },
        //snackbarHost = { CustomSnackBar(snackbarHostState = snackbarHostState) }
    ) {
        PokemonDetailsContent(
            userAppTheme = userAppTheme,
            pokemon = pokemon,
            pokemonDetailsUiState = pokemonDetailsUiState,
            animatedVisibilityScope = animatedVisibilityScope,
            onFetchDetails = { onFetchDetails() },
        ) { pokemon, isAdded ->
            onAddTeamMember(pokemon, isAdded)
            coroutineScope.launch {
//                if (isAdded) {
//                    SnackbarController.sendSnackbarEvent(event = SnackbarEvent(snackbarMessage))
//                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.PokemonDetailsContent(
    userAppTheme: AppThemeEnum,
    pokemon: Pokemon,
    pokemonDetailsUiState: PokemonDetailsUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onFetchDetails: () -> Unit,
    onAddTeamMember: (Pokemon, Boolean) -> Unit,
) {
    val scrollState = rememberScrollState()
    val topPaddingCard = 100

    // Minimize image when user scrolls and landscape is active
    val animatedImageSize by animateDpAsState(
        targetValue = if (scrollState.value > 60) (topPaddingCard / 4).dp else topPaddingCard.dp,
        label = "animatedImageSize"
    )

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .background(getColorWithGradient(userAppTheme, pokemon.color))
            .statusBarsPadding()
            .systemBarsPadding(),
    ) {
        PokemonCard(
            modifier = Modifier.padding(top = topPaddingCard.dp),
            pokemon = pokemon,
            pokemonDetailsUiState = pokemonDetailsUiState,
            onRetry = { onFetchDetails() }
        )
        PokemonImageAnimation(
            animatedVisibilityScope = animatedVisibilityScope,
            pokemon = pokemon,
            pokemonImageSize = animatedImageSize.times(2)
        )
        AddMemberButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = topPaddingCard.dp - 16.dp,
                    start = 32.dp,
                ),
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
                .size(if (isTablet()) 60.dp else 40.dp)
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
                    .padding(4.dp),
                imageVector = if (isTeamMember) Icons.Filled.PersonRemove else Icons.Outlined.PersonAdd,
                contentDescription = "Add member icon",
                tint = MaterialTheme.colorScheme.primary,
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
            .fillMaxWidth()
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (pokemonDetailsUiState) {
            is PokemonDetailsUiState.Loading -> {
                FullScreenLoader()
            }

            is PokemonDetailsUiState.Error -> {
                AppErrorDialog(appError = pokemonDetailsUiState.error)
                Button(
                    contentPadding = PaddingValues(16.dp),
                    onClick = { onRetry() }
                ) {
                    Text(text = stringResource(Res.string.retry_btn))
                }
            }

            is PokemonDetailsUiState.Success -> {
                PokemonInfo(pokemon = pokemon, details = pokemonDetailsUiState.details)
            }
        }
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
                0.dp,
                300.dp
            ), // set max height due to nested scroll need to have a defined height
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
                .applyIfNotCurrentLocalInspectionMode {
                    sharedElement(
                        state = rememberSharedContentState(key = "item-image${pokemon.id}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
        )
    }
}

@Composable
fun AppErrorDialog(appError: AppError) {
//    LocalContext.current.SimpleActionAlertDialog(
//        show = true,
//        title = appError.data?.code,
//        description = appError.data?.detail,
//    )
}