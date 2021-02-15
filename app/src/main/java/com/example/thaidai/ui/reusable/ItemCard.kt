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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.selection.Selection
import androidx.compose.ui.selection.SelectionContainer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thaidai.R
import com.example.thaidai.backend.domain.item.Item
import com.example.thaidai.ui.theme.ThaiDaiTheme
import com.example.thaidai.util.DEFAULT_IMAGE
import com.example.thaidai.util.loadPicture

@Composable
fun ItemCard(
    item: Item,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.small,
        elevation = 4.dp
    ) {
        Row {
            item.image?.let { url ->

                val image = loadPicture(url = url, defaultImage = DEFAULT_IMAGE)
                    .value

                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        modifier = Modifier
                            .preferredWidth(125.dp)
                            .preferredHeight(125.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
//                    .preferredHeight(125.dp)
            ) {
                item.names?.let { names ->
                    Text(
                        text = names?.nameEn,
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = names?.nameTh,
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray
                    )
                    Text(
                        text = names?.namePron,
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
