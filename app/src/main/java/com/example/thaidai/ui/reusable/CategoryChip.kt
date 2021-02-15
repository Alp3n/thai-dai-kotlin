package com.example.thaidai.ui.reusable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean = false,
    onSelectedCategoryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 6.dp,
        shape = MaterialTheme.shapes.medium,
        color = if(isSelected) Color.LightGray else Color(0xFF689f38)
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChange(category)
                    onExecuteSearch()
                }
            )
        ) {
            Text(
                text = category.toUpperCase(),
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}