package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.BooksTheme
import com.example.myapplication.ui.theme.Secondary
import com.example.myapplication.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LibraryScreen(openBookDetailsScreen: () -> Unit) {
    val items = listOf(
        GridItem(
            painterResource(R.drawable.image),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image1),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image2),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image3),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image4),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image5),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image6),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image1),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image2),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image3),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image4),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image5),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image6),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image1),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image2),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image3),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image4),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        )
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        BooksTheme {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.library),
                        style = BooksTheme.typography.h1.copy(
                            color = Secondary
                        )
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        text = stringResource(R.string.news),
                        style = BooksTheme.typography.h2.copy(
                            color = AccentDark
                        )
                    )
                }

                item {
                    HeroCenteredCarousel(openBookDetailsScreen)
                }

                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        text = stringResource(R.string.popular_books),
                        style = BooksTheme.typography.h2.copy(
                            color = AccentDark
                        )
                    )
                }

                item {
                    LazyVerticalGrid(
                        userScrollEnabled = false,
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 20000.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(items.size) { index ->
                            val item = items[index]
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(0.45f)
                            ) {
                                Image(
                                    painter = item.painter,
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                        .weight(2f, true)
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxSize()
                                        .padding(top = 16.dp)
                                        .clickable { openBookDetailsScreen.invoke() }
                                )
                                Text(
                                    item.name.uppercase(),
                                    modifier = Modifier.padding(top = 8.dp),
                                    style = BooksTheme.typography.bookName.copy(color = AccentDark)
                                )
                                Text(
                                    item.author,
                                    modifier = Modifier.padding(top = 4.dp),
                                    style = BooksTheme.typography.footnote.copy(color = AccentDark)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeroCenteredCarousel(openBookDetailsScreen: () -> Unit) {
    val items = listOf(
        GridItem(
            painterResource(R.drawable.image),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image1),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image2),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image3),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image4),
            "Понедельник начинается в субботуб",
            "Эрик Мария Ремарк"
        ), GridItem(
            painterResource(R.drawable.image5),
            "Понедельник начинается в субботу",
            "Эрик Мария Ремарк"
        )
    )

    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            coroutineScope.launch {
                val currentItem = state.firstVisibleItemIndex
                val nextItem = (currentItem + 1) % items.size
                state.animateScrollToItem(nextItem)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 80.dp)
        ) {

            items(items.size) { index ->
                val item = items[index]
                val isCentered = state.firstVisibleItemIndex == index
                Box {
                    Image(
                        painter = item.painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .aspectRatio(1f)
                            .clickable { openBookDetailsScreen.invoke() }
                    )
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .align(Alignment.BottomStart)
                            .aspectRatio(1f)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Black.copy(0.5f)),
                                )
                            )
                    )
                    if (isCentered) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillParentMaxWidth()
                        ) {

                            Text(
                                item.name,
                                softWrap = true,
                                modifier = Modifier.padding(
                                    bottom = 4.dp,
                                    start = 16.dp,
                                    end = 16.dp
                                ),
                                style = BooksTheme.typography.bodySmall.copy(color = White)
                            )
                            Text(
                                item.author.uppercase(),
                                softWrap = true,
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                style = BooksTheme.typography.h2.copy(color = White)
                            )
                        }
                    }
                }

            }
        }
    }
}

data class GridItem(val painter: Painter, val name: String, val author: String)