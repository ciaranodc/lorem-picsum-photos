@file:OptIn(ExperimentalMaterial3Api::class)

package com.lorempicsum.photos.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lorempicsum.photos.ui.images.DEFAULT_AUTHOR_SELECTION

@Composable
fun CustomDropdownMenu(menuItems: List<String>, initialSelection: String, onSelectAuthor: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .weight(1f),
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }
        ) {
            TextField(
                value = initialSelection,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                menuItems.forEach { item ->
                    DropdownMenuItem(text = {
                        Text(text = item)
                    }, onClick = {
                        onSelectAuthor(item)
                        isExpanded = false
                    })
                }
            }
        }

        Button(
            shape = RoundedCornerShape(10),
            onClick = {
                onSelectAuthor(DEFAULT_AUTHOR_SELECTION)
            }
        ) {
            Text(text = "CLEAR")
        }
    }
}