package com.example.tmdb.navigation


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.example.tmdb.utils.Constants.DEFAULT_ANIMATION_DURATION


fun defaultEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)) +
            slideInHorizontally(
                animationSpec = tween(DEFAULT_ANIMATION_DURATION),
            )
}

fun defaultExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(DEFAULT_ANIMATION_DURATION)) +
            slideOutHorizontally(
                animationSpec = tween(DEFAULT_ANIMATION_DURATION),
            )
}

