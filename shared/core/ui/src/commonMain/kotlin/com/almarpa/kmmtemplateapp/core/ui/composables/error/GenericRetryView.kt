package com.almarpa.kmmtemplateapp.core.ui.composables.error

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GenericRetryView(
    modifier: Modifier = Modifier,
    errorDescription: String = "Error",
    actionBtnText: String = "Reintentar",
    action: () -> Unit = {},
) {
    Column(
        modifier = modifier.padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            text = errorDescription,
        )
        Button(
            contentPadding = PaddingValues(16.dp),
            onClick = { action() }
        ) {
            Text(text = actionBtnText)
        }
    }
}