package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.AccentLight
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.BooksTheme
import com.example.myapplication.ui.theme.Secondary


@Composable
fun ChapterScreen(
    openChapterScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    var title: String? by remember { mutableStateOf(null) }
    var chapter: String? by remember { mutableStateOf(null) }
    var annotatedText: AnnotatedString? by remember { mutableStateOf(null) }
    var isPlaying: Boolean by remember { mutableStateOf(false) }
    var current: Boolean by remember { mutableStateOf(false) }
    var currentSentenceIndex by remember { mutableStateOf(0) }

    //
    title = "КОД ДА ВИНЧИ"
    chapter = "Пролог"
    val text =
        "Знаменитый куратор Жак Соньер, пошатыва\u0002ясь, прошел под сводчатой аркой Большой га\u0002лереи и устремился к первой попавшейся ему\n" +
                "на глаза картине, полотну Караваджо. Ухватил\u0002ся руками за позолоченную раму и стал тянуть\n" +
                "ее на себя, пока шедевр не сорвался со стены и\n" +
                "не рухнул на семидесятилетнего старика Соньера, погребя его под собой.\n" +
                "Как и предполагал Соньер, неподалеку с грохотом опустилась\n" +
                "металлическая решетка, преграждающая доступ в этот зал. Пар\u0002кетный пол содрогнулся. Где-то завыла сирена игнализации.\n" +
                "Несколько секунд куратор лежал неподвижно, хватая ртом\n" +
                "воздух и пытаясь сообразить, на каком свете находится. Я все еще жив. Потом он выполз из-под полотна и начал судорожно ози\u0002раться в поисках места, где можно спрятаться.\n" +
                "Голос прозвучал неожиданно близко:\n" + "\n\n\n\n\n\n\n\n\n\n" +
                "— Не двигаться.\n" +
                "Стоявший на четвереньках куратор похолодел, потом медлен\u0002но обернулся. Всего в пятнадцати футах от него, за решеткой, высилась внушительная и грозная фигура его преследователя. Вы\u0002сокий, широкоплечий, с мертвенно-бледной кожей и редкими\n" +
                "белыми волосами. Белки розовые, а зрачки угрожающего темно\u0002красного цвета. Альбинос достал из кармана пистолет, сунул\n" +
                "длинный ствол в отверстие между железными прутьями и при\u0002целился в куратора.\n"
    val sentences = text.split(". ")
    annotatedText = buildAnnotatedString {
        sentences.forEachIndexed { index, sentence ->
            val isCurrentSentence = index == currentSentenceIndex
            withStyle(
                style = SpanStyle(
                    color = if (isCurrentSentence) Secondary else Black,
                )
            ) {
                append(sentence)
                append(". ")
            }
        }
    }
    //

    Scaffold(
        bottomBar = {
            Footer()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(Background)
                .padding(16.dp)
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Header(
                title = title,
                chapter = chapter,
                openChapterScreen = openChapterScreen
            )
            Box {
                annotatedText?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(top = 16.dp),
                        style = BooksTheme.typography.text,
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Background, Color.Transparent),
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Background),
                            )
                        )
                )
            }
        }
    }
}

@Composable
private fun Footer(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AccentDark)
            .padding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .navigationBarsPadding()
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = AccentDark,
                contentColor = Background
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.previous),
                contentDescription = "Previous button",
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .padding(start = 16.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = AccentDark,
                contentColor = Background
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.contents),
                contentDescription = "Previous button",
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .padding(start = 16.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = AccentDark,
                contentColor = Background
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.next),
                contentDescription = "Previous button",
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .padding(start = 16.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = AccentDark,
                contentColor = Background
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.settings),
                contentDescription = "Previous button",
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = AccentLight,
            modifier = Modifier.size(56.dp)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(56.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.play),
                    contentDescription = "Previous button",
                )
            }
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    title: String?,
    chapter: String?,
    openChapterScreen: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = openChapterScreen,
            modifier = Modifier
                .padding(4.dp)
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
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            title?.let {
                Text(
                    text = it,
                    style = BooksTheme.typography.h2,
                    color = AccentDark
                )
            }
            chapter?.let {
                Text(
                    text = it,
                    style = BooksTheme.typography.bodySmall,
                    color = AccentDark
                )
            }
        }
        Spacer(modifier = Modifier.size(48.dp))
    }
}