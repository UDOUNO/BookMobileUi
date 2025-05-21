package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.booksapp.presentation.common.ui_elements.ReadProgressIndicator
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.AccentLite
import com.example.myapplication.ui.theme.AccentMedium
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.BooksTheme
import com.example.myapplication.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookDetails(openBookDetailsScreen: () -> Unit, openChapterScreen: () -> Unit) {
    val items = listOf(
        TitledPartItem("Пролог", false, true),
        TitledPartItem("Глава 1", true, false),
        TitledPartItem("Глава 2", false, false),
        TitledPartItem("Глава 3", false, false),
        TitledPartItem("Глава 4", false, false),
        TitledPartItem("Глава 5", false, false),
        TitledPartItem("Глава 6", false, false),
        TitledPartItem("Глава 7", false, false),
        TitledPartItem("Глава 8", false, false),
        TitledPartItem("Глава 9", false, false),
        TitledPartItem("Глава 10", false, false)
    )
    var progressFraction by remember { mutableFloatStateOf(0f) }
    var cover by remember { mutableIntStateOf(0) }
    var description by remember { mutableStateOf("") }

    description = "Секретный код скрыт в работах Леонардо да Винчи...\n" +
            "Только он поможет найти христианские святыни, дающие немыслимые власть и могущество... \n" +
            "Ключ к величайшей тайне, над которой человечество билось веками, наконец может быть найден..."
    cover = R.drawable.image12
    progressFraction = 0.2f

    Scaffold { padding ->
        BooksTheme {
            LazyColumn(Modifier.background(Background)) {
                item {
                    Box(modifier = Modifier.aspectRatio(1f)) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(cover),
                            contentDescription = null
                        )
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .aspectRatio(2.3f)
                                .align(Alignment.BottomCenter)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Background),
                                    )
                                )
                        )
                        IconButton(
                            onClick = openBookDetailsScreen,
                            modifier = Modifier
                                .padding(top = 4.dp, start = 16.dp)
                                .padding(padding)
                                .size(40.dp)
                                .clip(CircleShape),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = AccentDark,
                                contentColor = Background
                            )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_left),
                                contentDescription = "Backstack button",
                                modifier = Modifier
                            )
                        }

                        Row(
                            Modifier
                                .padding(horizontal = 16.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            Button(
                                onClick = openChapterScreen,
                                colors = ButtonDefaults.buttonColors(containerColor = AccentDark)
                            ) {

                                Icon(
                                    painter = painterResource(R.drawable.play),
                                    contentDescription = null,
                                    Modifier
                                        .padding(
                                            top = 4.dp,
                                            end = 8.dp,
                                            bottom = 4.dp,
                                            start = 40.dp
                                        )
                                        .size(16.dp)
                                )

                                Text(
                                    modifier = Modifier.padding(
                                        end = 40.dp,
                                        top = 4.dp,
                                        bottom = 4.dp
                                    ),
                                    text = stringResource(R.string.read),
                                    style = BooksTheme.typography.bodySmall.copy(
                                        color = White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Button(
                                onClick = {},
                                Modifier.padding(start = 8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = AccentLite)
                            ) {
                                Icon(
                                    tint = AccentDark,
                                    painter = painterResource(R.drawable.bookmarks),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(top = 4.dp, bottom = 4.dp, end = 8.dp)
                                        .size(16.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                                    text = stringResource(R.string.to_fav),
                                    style = BooksTheme.typography.bodySmall.copy(
                                        color = AccentDark,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    }
                }
                item {
                    Title(Author("КОД ДА ВИНЧИ", "Дэн Браун"))
                }
                item {
                    Description(description)
                }
                item {
                    Progress(progressFraction)
                }
                item {
                    Text(
                        text = stringResource(R.string.title),
                        Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = BooksTheme.typography.h2.copy(
                            color = AccentDark
                        )
                    )
                }
                items(items.size) { index ->
                    val item = items[index]
                    TitleItems(item, openChapterScreen)
                }
            }
        }
    }
}

@Composable
fun Progress(progress: Float) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = stringResource(R.string.readed),
            style = BooksTheme.typography.h2.copy(
                color = AccentDark
            )
        )
        ReadProgressIndicator(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            progressFraction = progress
        )
    }
}

@Composable
fun Title(item: Author) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = item.title,
            style = BooksTheme.typography.h1.copy(
                color = AccentDark
            )
        )
        Text(
            text = item.name,
            style = BooksTheme.typography.body.copy(
                color = AccentDark
            )
        )
    }
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        style = BooksTheme.typography.body.copy(
            color = AccentDark
        )
    )
}

@Composable
fun TitleItems(item: TitledPartItem, openChapterScreen: () -> Unit) {
    Box(
        Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = item.number,
            style = BooksTheme.typography.body.copy(
                color = AccentDark,
                fontWeight = if (item.chosen) FontWeight.Bold else FontWeight.Normal
            )
        )
        if (item.read) {
            Icon(
                painter = painterResource(R.drawable.read),
                modifier = Modifier.align(Alignment.CenterEnd),
                contentDescription = null,
                tint = AccentMedium
            )
        } else if (item.chosen) {
            Icon(
                painter = painterResource(R.drawable.reading_now),
                modifier = Modifier.align(Alignment.CenterEnd).clickable { openChapterScreen.invoke() },
                contentDescription = null,
                tint = AccentDark
            )
        }
    }
}

