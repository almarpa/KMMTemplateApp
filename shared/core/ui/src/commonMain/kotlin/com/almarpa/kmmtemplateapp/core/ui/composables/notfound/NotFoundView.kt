package com.almarpa.kmmtemplateapp.core.ui.composables.notfound

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NotFoundView(description: String) {
    Column(
        modifier = Modifier.padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "404",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            text = description,
        )
    }
}

@Preview()
@Composable
fun NotFoundViewPreview() {
    AppTheme {
        NotFoundView("Not found")
    }
}