@file:OptIn(ExperimentalMaterial3Api::class)

package com.almarpa.kmmtemplateapp.core.presentation.composables.dropdown

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomDropdown(
    modifier: Modifier = Modifier,
    items: Map<String, String>,
    selectedKey: String,
    onClickItem: (selectionKey: String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedValue = items[selectedKey] ?: items.values.firstOrNull().orEmpty()

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true),
            readOnly = true,
            value = selectedValue,
            onValueChange = { },
            trailingIcon = { TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                val isSelected = item.key == selectedKey
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.value,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    },
                    onClick = {
                        expanded = false
                        onClickItem(item.key)
                    }
                )
            }
        }
    }
}

@Preview()
@Composable
fun CustomDropdownPreview() {
    CustomDropdown(
        items = mapOf("Test" to "Test"),
        selectedKey = "Test",
    )
}