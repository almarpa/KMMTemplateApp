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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorPlaceholderView(
    modifier: Modifier = Modifier,
    errorDescription: String,
    actionText: String? = null,
    action: () -> Unit = {},
) {
    Column(
        modifier = modifier.padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            text = errorDescription,
        )
        actionText?.let { actionTextNotNull ->
            Button(
                modifier = Modifier.padding(top = 16.dp),
                contentPadding = PaddingValues(16.dp),
                onClick = { action() }
            ) {
                Text(text = actionTextNotNull)
            }
        }
    }
}

@Preview
@Composable
fun ErrorPlaceholderViewWithActionPreview() {
    ErrorPlaceholderView(
        errorDescription = "Error getting pokemon list",
        actionText = "Retry"
    )
}

@Preview()
@Composable
fun ErrorPlaceholderViewPreview() {
    ErrorPlaceholderView(
        errorDescription = "Error getting pokemon list",
        actionText = null
    )
}
