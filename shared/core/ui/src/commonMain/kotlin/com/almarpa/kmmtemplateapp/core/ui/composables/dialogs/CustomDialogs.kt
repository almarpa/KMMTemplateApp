package com.almarpa.kmmtemplateapp.core.ui.composables.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SimpleActionAlertDialog(
    show: Boolean,
    title: String,
    description: String = "",
    confirmText: String,
    onAccept: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onAccept()
                    }
                ) {
                    Text(text = confirmText)
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
        )
    }
}


@Composable
fun CustomDialog(
    show: Boolean = true,
    title: String,
    description: String = "",
    onConfirm: () -> Unit = {},
    confirmText: String,
    onCancel: () -> Unit = {},
    cancelText: String,
    onDismissRequest: () -> Unit = {},
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(20.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        text = title,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 30.dp),
                        fontSize = 16.sp,
                        text = description,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = { onCancel() }) {
                            Text(text = cancelText)
                        }
                        Button(onClick = { onConfirm() }) {
                            Text(text = confirmText)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun SimpleAlertDialogPreview() {
    SimpleActionAlertDialog(
        show = true,
        title = "Search",
        description = "Error getting pokemon list",
        confirmText = "Accept"
    )
}

@Preview
@Composable
fun CustomDialogPreview() {
    CustomDialog(
        title = "Search",
        description = "Error getting pokemon list",
        confirmText = "Accept",
        cancelText = "Cancel",
    )
}