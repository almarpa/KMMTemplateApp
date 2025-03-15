package com.almarpa.kmmtemplateapp.core.common.extensions

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import com.almarpa.kmmtemplateapp.core.common.model.enums.AnimState

fun Modifier.shimmerLoadingAnimation(
    isLoadingCompleted: Boolean = false,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier {
    return if (isLoadingCompleted) {
        this
    } else {
        composed {
            val shimmerColors = with(Color.White) {
                listOf(
                    copy(alpha = 0.3f),
                    copy(alpha = 0.5f),
                    copy(alpha = 1.0f),
                    copy(alpha = 0.5f),
                    copy(alpha = 0.3f),
                )
            }
            val transition = rememberInfiniteTransition(label = "Shimmer transition animation")
            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "Shimmer loading animation",
            )
            background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                    end = Offset(x = translateAnimation.value, y = angleOfAxisY),
                ),
            )
        }
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun SharedTransitionScope.modifierWithSharedElementTransition(
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: SharedTransitionScope.SharedContentState,
): Modifier =
    if (!LocalInspectionMode.current) {
        Modifier.sharedElement(
            state = state,
            animatedVisibilityScope = animatedVisibilityScope,
        )
    } else {
        Modifier
    }

@Composable
fun modifierWithLazyGridAnimationPreview(index: Int, columns: Int) =
    if (!LocalInspectionMode.current) {
        Modifier.applyAlphaScaleAnimation(index, columns)
    } else {
        Modifier
    }

@Composable
fun Modifier.applyAlphaScaleAnimation(index: Int, columns: Int): Modifier {
    val animation: FiniteAnimationSpec<Float> = tween(
        durationMillis = 400,
        delayMillis = index % columns * 50,
        easing = LinearOutSlowInEasing
    )
    val transitionState = remember {
        MutableTransitionState(AnimState.PLACING).apply { targetState = AnimState.PLACED }
    }
    val transition = rememberTransition(transitionState)
    val alpha by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            AnimState.PLACING -> 0f
            AnimState.PLACED -> 1f
        }
    }
    val scale by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            AnimState.PLACING -> 0f
            AnimState.PLACED -> 1f
        }
    }
    return graphicsLayer(
        alpha = alpha,
        scaleX = scale,
        scaleY = scale,
    )
}
