package com.example.thaidai.ui.reusable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.selection.Selection
import androidx.compose.ui.selection.SelectionContainer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thaidai.R
import com.example.thaidai.ui.theme.ThaiDaiTheme

@Composable
fun ItemCard(
    itemNameEn: String,
    itemNameTh: String,
    itemNamePron: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick),
        shape = RoundedCornerShape (16.dp),
    ) {
        Row(
//                verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageResource(id = R.drawable.thaibasil),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .preferredWidth(125.dp)
                    .preferredHeight(125.dp)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
//                    .preferredSizeIn(minWidth = 200.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = itemNameEn,
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = itemNameTh,
                    style = MaterialTheme.typography.h6,
                    color = Color.Gray
                )
                Text(
                    text = itemNamePron,
                    style = MaterialTheme.typography.h6,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
@Preview
fun ItemCardPreview() {
    ThaiDaiTheme(darkTheme = false) {
        ItemCard("Thai basil", "หกฟ้ๆไ", "krapao", onClick = {})
    }

}
