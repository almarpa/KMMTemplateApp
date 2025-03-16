package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.composables.snackbar.CustomSnackBar
import com.almarpa.kmmtemplateapp.core.ui.composables.snackbar.showSnackbar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewCustomSnackBar() {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { CustomSnackBar(snackBarHostState) }
    ) {
        coroutineScope.showSnackbar(
            snackbarHostState = snackBarHostState,
            message = "Scaffold SnackBar Preview",
            actionLabel = "Action",
        )
    }
}