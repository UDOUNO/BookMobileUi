package com.example.booksapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.booksapp.presentation.common.ui_elements.BookItemCard
import com.example.booksapp.presentation.models.AuthorListItem
import com.example.booksapp.presentation.models.BookListItem
import com.example.booksapp.presentation.models.GenreListItem
import com.example.booksapp.presentation.models.SearchListItem
import com.example.myapplication.R
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.AccentLight
import com.example.myapplication.ui.theme.AccentMedium
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.BooksTheme
import com.example.myapplication.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    fabActive: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    openDetailsScreen: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var isSearchActive = remember { mutableStateOf(false) }
    var searchBookItems: List<BookListItem>? by remember { mutableStateOf(null) }
    var latestSearches: List<SearchListItem>? by remember { mutableStateOf(null) }
    var genres: List<GenreListItem>? by remember { mutableStateOf(null) }
    var authors: List<AuthorListItem>? by remember { mutableStateOf(null) }

    val avatar = painterResource(R.drawable.brothers)
    val book = painterResource(R.drawable.image2)


    latestSearches = listOf(
        SearchListItem("iOS"),
        SearchListItem("iOSsssssssssssssssssssssssssssssssssssssssssssss"),
        SearchListItem("iOSssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"),
    )
    genres = listOf(
        GenreListItem("Классикаааааааааааааааа"),
        GenreListItem("Фантастика"),
        GenreListItem("Фентези")
    )
    authors = listOf(
        AuthorListItem("Братья Стругацкие", avatar),
        AuthorListItem(
            "Братья Стругацкие, Братья Стругацкие, Братья Стругацкие, Братья Стругацкие,  Братья Стругацкие, Братья Стругацкие, ",
            avatar
        ),
        AuthorListItem("Братья Стругацкие", avatar),
        AuthorListItem("Братья Стругацкие", avatar),
        AuthorListItem("Братья Стругацкие", avatar),
    )
    searchBookItems = listOf(
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", book),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", book),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", book),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", book),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", book),
        BookListItem("КОД ДА ВИНЧИ", "Ден Браун", book)
    )
    //

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 16.dp)
            .imePadding()
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (isSearchActive.value) 0.dp else 16.dp),
            colors = SearchBarDefaults.colors(
                dividerColor = AccentDark,
                containerColor = if (isSearchActive.value) Background else White,
                inputFieldColors = TextFieldDefaults.colors(
                    unfocusedTextColor = AccentMedium,
                    focusedTextColor = AccentDark,
                    cursorColor = Secondary
                ),
            ),
            query = searchText,
            onQueryChange = { searchText = it },
            onSearch = {},
            active = isSearchActive.value,
            onActiveChange = { isSearchActive.value = it },
            placeholder = {
                Text(
                    stringResource(R.string.books_search),
                    color = AccentMedium
                )
            },
            leadingIcon = {
                if (isSearchActive.value) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = AccentDark
                        ),
                        onClick = { isSearchActive.value = false }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_left),
                            contentDescription = "Back"
                        )
                    }
                } else {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = AccentMedium
                        ),
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "Search"
                        )
                    }
                }
            },
            trailingIcon = {
                if (isSearchActive.value) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = AccentDark
                        ),
                        onClick = { searchText = "" }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.close),
                            contentDescription = "Clear"
                        )
                    }
                }
            },
        ) {
            SearchList(searchBookItems, openDetailsScreen)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            latestSearches?.let {
                if (it.isEmpty()) return@let

                item {
                    Text(
                        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        text = stringResource(R.string.latest_searches),
                        style = BooksTheme.typography.h2.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = AccentDark
                    )
                }

                items(it.size) { index ->
                    SearchItem(
                        modifier = Modifier.padding(top = 8.dp),
                        item = it[index]
                    )
                }
            }
            genres?.let {
                if (it.isEmpty()) return@let

                item {
                    Text(
                        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                        text = stringResource(R.string.genres),
                        style = BooksTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                        color = AccentDark
                    )
                }

                items((it.size + 1) / 2) { index ->
                    val firstItem = it[index * 2]
                    val secondItem = if (index * 2 + 1 < it.size) it[index * 2 + 1] else null

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(top = 8.dp),
                    ) {
                        GenresItem(
                            modifier = Modifier
                                .weight(1f),
                            item = firstItem, isSearchActive
                        )
                        secondItem?.let {
                            GenresItem(
                                modifier = Modifier
                                    .weight(1f),
                                item = secondItem, isSearchActive
                            )
                        } ?: Spacer(
                            modifier = Modifier
                                .weight(1f),
                        )
                    }
                }
            }
            authors?.let {
                if (it.isEmpty()) return@let

                item {
                    Text(
                        modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                        text = stringResource(R.string.authors),
                        style = BooksTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                        color = AccentDark
                    )
                }

                items(it.size) { index ->
                    AuthorsItem(
                        modifier = Modifier.padding(bottom = 8.dp),
                        item = it[index],
                        isSearchActive
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchList(searchBookItems: List<BookListItem>?, openDetailsDestination: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        searchBookItems?.let {
            items(it.size) { index ->
                BookItemCard(
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    item = it[index],
                    openDetailsDestination
                )
            }
        }
    }
}

@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    item: SearchListItem
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(AccentLight)
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.history),
            contentDescription = "Search from history"
        )
        Text(
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f),
            text = item.text,
            style = BooksTheme.typography.bodySmall,
            color = AccentDark
        )
        Icon(
            painter = painterResource(R.drawable.close),
            contentDescription = "Search from history"
        )
    }
}

@Composable
private fun GenresItem(
    modifier: Modifier = Modifier,
    item: GenreListItem,
    isSearchActive: MutableState<Boolean>
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(AccentLight)
            .padding(16.dp)
            .clickable { isSearchActive.value = true },
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        text = item.title,
        style = BooksTheme.typography.bodySmall,
        color = AccentDark
    )
}

@Composable
private fun AuthorsItem(
    modifier: Modifier = Modifier,
    item: AuthorListItem,
    isSearchActive: MutableState<Boolean>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(AccentLight)
            .padding(16.dp)
            .clickable { isSearchActive.value = true }
    ) {
        Image(
            painter = item.avatar,
            contentDescription = "Book cover",
            modifier = Modifier
                .size(48.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop,
        )
        Text(
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            text = item.name,
            style = BooksTheme.typography.body,
            color = AccentDark
        )
    }
}