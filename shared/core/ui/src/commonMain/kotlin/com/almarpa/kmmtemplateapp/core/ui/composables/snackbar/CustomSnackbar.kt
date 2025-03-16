package com.almarpa.kmmtemplateapp.core.ui.composables.snackbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomSnackBar(snackbarHostState: SnackbarHostState) {
    snackbarHostState.currentSnackbarData?.let { data ->
        Snackbar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            actionColor = MaterialTheme.colorScheme.onPrimary,
            actionContentColor = MaterialTheme.colorScheme.onPrimary,
            snackbarData = data,
        )
    }
}

fun CoroutineScope.showSnackbar(
    snackbarHostState: SnackbarHostState,
    message: String = "SnackBar Preview",
    duration: SnackbarDuration = SnackbarDuration.Short,
    actionLabel: String? = null,
    onActionPerformed: () -> Unit = {},
    onDismissed: () -> Unit = {},
) {
    launch {
        // Dismiss previous existing snackbar
        snackbarHostState.currentSnackbarData?.dismiss()

        val action = snackbarHostState.showSnackbar(
            message = message,
            duration = duration,
            actionLabel = actionLabel,
            withDismissAction = true,
        )

        when (action) {
            SnackbarResult.ActionPerformed -> {
                onActionPerformed()
            }

            SnackbarResult.Dismissed -> {
                onDismissed()
            }
        }
    }
}