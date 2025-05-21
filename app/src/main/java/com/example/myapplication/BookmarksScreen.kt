package com.example.booksapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.booksapp.presentation.common.ui_elements.BookItemCard
import com.example.booksapp.presentation.common.ui_elements.ReadProgressIndicator
import com.example.booksapp.presentation.models.BookListItem
import com.example.booksapp.presentation.models.QuoteItem
import com.example.booksapp.presentation.models.ReadProgressBookItem
import com.example.myapplication.R
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.AccentMedium
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.BooksTheme
import com.example.myapplication.ui.theme.Secondary

@Composable
fun BookmarksScreen(
    modifier: Modifier = Modifier
) {
    var readNowSectionItem: ReadProgressBookItem? by remember { mutableStateOf(null) }
    var favoriteBooksSectionItems: List<BookListItem>? by remember { mutableStateOf(null) }
    var quotesSectionItems: List<QuoteItem>? by remember { mutableStateOf(null) }

    val bookImage = painterResource(R.drawable.image3)

    readNowSectionItem = ReadProgressBookItem("КОД ДА ВИНЧИ", "Пролог", bookImage, 0.2f)

    favoriteBooksSectionItems = listOf(
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", bookImage),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", bookImage),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", bookImage),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", bookImage)
    )

    quotesSectionItems = listOf(
        QuoteItem("Я все еще жив", "Код да Винчи", "Ден Браун"),
        QuoteItem("Я все еще жив", "Код да Винчи", "Ден Браун")
    )

    Scaffold{padding ->
        BooksTheme {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(padding)
                    .then(modifier)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        modifier = modifier
                            .padding(top = 24.dp),
                        text = stringResource(R.string.bookmarks),
                        style = BooksTheme.typography.h1.copy(
                            fontWeight = FontWeight.Bold,
                            color = Secondary,
                        ),
                    )
                }

                item {
                    readNowSectionItem?.let {
                        ReadNowSectionTitle(onReadButtonClick = {})
                        ReadNowSectionItem(item = it)
                    }
                }

                favoriteBooksSectionItems?.let {
                    if (it.isEmpty()) return@let

                    item {
                        Text(
                            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                            text = stringResource(R.string.favorite_books),
                            style = BooksTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                            color = AccentDark
                        )
                    }

                    items(it.size) { index ->
                        BookItemCard(
                            modifier = Modifier.padding(top = 8.dp),
                            item = it[index],
                            null
                        )
                    }
                }

                quotesSectionItems?.let {
                    if (it.isEmpty()) return@let

                    item {
                        Text(
                            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                            text = stringResource(R.string.quotes),
                            style = BooksTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                            color = AccentDark
                        )
                    }

                    items(it.size) { index ->
                        QuoteItem(item = it[index])
                    }
                }
            }
        }
    }

}

@Composable
private fun QuoteItem(
    modifier: Modifier = Modifier,
    item: QuoteItem
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(AccentMedium)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = item.text,
            style = BooksTheme.typography.quote,
            fontStyle = FontStyle.Italic,
            color = Black
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = item.title + " # " + item.authors,
            style = BooksTheme.typography.footnote,
            color = AccentDark
        )
    }
}

@Composable
private fun ReadNowSectionItem(
    modifier: Modifier = Modifier,
    item: ReadProgressBookItem
) {
    Row(
        modifier = modifier,
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
                style = BooksTheme.typography.h2,
                color = AccentDark
            )
            Text(
                text = item.part,
                style = BooksTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = AccentDark
            )
            Spacer(modifier = Modifier.height(16.dp))
            ReadProgressIndicator(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                progressFraction = item.fraction
            )
        }
    }
}

@Composable
private fun ReadNowSectionTitle(
    modifier: Modifier = Modifier,
    onReadButtonClick: () -> Unit
) {
    BooksTheme {
        Row(
            modifier = modifier
                .padding(top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.read_now),
                style = BooksTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                color = AccentDark
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onReadButtonClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = AccentDark,
                    contentColor = Background
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.play),
                    contentDescription = "Play last book",
                    modifier = Modifier
                )
            }
        }
    }
}
