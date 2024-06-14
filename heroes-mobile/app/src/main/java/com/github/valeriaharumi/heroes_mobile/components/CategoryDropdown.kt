package com.github.valeriaharumi.heroes_mobile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun CategoryDropdown(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Column {
        OutlinedButton(onClick = { expanded.value = !expanded.value }) {
            Text(text = selectedCategory.ifEmpty { "Selecionar Categoria" })
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                DropdownMenuItem(onClick = {
                    onCategorySelected(category)
                    expanded.value = false
                }) {
                    Text(text = category)
                }
            }
        }
    }
}