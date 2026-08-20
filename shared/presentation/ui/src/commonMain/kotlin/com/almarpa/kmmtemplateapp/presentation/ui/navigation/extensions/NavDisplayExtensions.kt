package com.almarpa.kmmtemplateapp.presentation.ui.navigation.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.scene.Scene
import androidx.navigation3.ui.defaultPopTransitionSpec
import androidx.navigation3.ui.defaultPredictivePopTransitionSpec
import androidx.navigation3.ui.defaultTransitionSpec
import com.almarpa.kmmtemplateapp.core.common.platform.isIosPlatform

fun <T : Any> AnimatedContentTransitionScope<Scene<T>>.sharedTransitionSpec(): ContentTransform {
    return if (isIosPlatform()) {
        defaultTransitionSpec<T>().invoke(this)
    } else {
        slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
    }
}

fun <T : Any> AnimatedContentTransitionScope<Scene<T>>.sharedPopTransitionSpec(): ContentTransform {
    return if (isIosPlatform()) {
        defaultPopTransitionSpec<T>().invoke(this)
    } else {
        slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
    }
}

fun <T : Any> AnimatedContentTransitionScope<Scene<T>>.sharedPredictivePopTransitionSpec(edge: Int): ContentTransform {
    return if (isIosPlatform()) {
        defaultPredictivePopTransitionSpec<T>().invoke(this, edge)
    } else {
        slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
    }
}
