package com.example.booksapp.presentation.common.ui_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.booksapp.presentation.models.BookListItem
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.BooksTheme

@Composable
fun BookItemCard(
    modifier: Modifier = Modifier,
    item: BookListItem,
    openDetailsScreen: (() -> Unit)?
) {
    Row(
        modifier = modifier
            .clickable {
                openDetailsScreen?.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = item.cover,
            contentDescription = "Book cover",
            modifier = Modifier
                .height(126.dp)
                .width(80.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = item.title,
                style = BooksTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                color = AccentDark
            )
            Text(
                text = item.authors,
                style = BooksTheme.typography.bodySmall,
                color = AccentDark
            )
        }
    }
}