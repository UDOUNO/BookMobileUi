package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.AccentLite
import com.example.myapplication.ui.theme.AccentMedium
import com.example.myapplication.ui.theme.BooksTheme
import com.example.myapplication.ui.theme.Secondary
import com.example.myapplication.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InfiniteCarousel(items: List<Painter>, function: () -> Unit) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val email = remember { mutableStateOf("") }
    val passwd = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(listState) {
        while (true) {
            delay(10)
            coroutineScope.launch {
                listState.scrollBy(1f)
            }
        }
    }

    LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemIndex } }) {
        if (listState.firstVisibleItemIndex >= items.size) {
            coroutineScope.launch {
                listState.scrollToItem(0)
            }
        }
    }

    Column(
        modifier = Modifier
            .background(AccentDark)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
    {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size * 100) { index ->
                val item = items[index % items.size]
                Box(
                    modifier = Modifier
                        .size(height = 300.dp, width = 150.dp)
                        .paint(painter = item, contentScale = ContentScale.Fit)
                ) {
                }
            }
        }
        BooksTheme {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

                Text(
                    style = BooksTheme.typography.h1,
                    text = stringResource(R.string.open_for_yourself),
                    color = AccentLite
                )

                Text(
                    style = BooksTheme.typography.title,
                    text = stringResource(R.string.books_world),
                    color = Secondary
                )
            }
        }

        BooksTheme {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {

                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedPlaceholderColor = AccentMedium,
                        focusedPlaceholderColor = AccentLite,
                        focusedBorderColor = AccentMedium,
                        unfocusedBorderColor = AccentMedium
                    ),
                    value = email.value,
                    onValueChange = { newText -> email.value = newText },
                    placeholder = { Text(stringResource(R.string.mail)) },
                    maxLines = 1,
                    textStyle = BooksTheme.typography.bodySmall.copy(
                        color = AccentLite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    trailingIcon = {
                        if (email.value.isNotEmpty()) {
                            IconButton(onClick = { email.value = "" }) {
                                Icon(
                                    tint = AccentLite,
                                    painter =
                                    painterResource(R.drawable.close),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                )

                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedPlaceholderColor = AccentMedium,
                        focusedPlaceholderColor = AccentLite,
                        focusedBorderColor = AccentMedium,
                        unfocusedBorderColor = AccentMedium
                    ),
                    value = passwd.value,
                    onValueChange = { newText -> passwd.value = newText },
                    placeholder = { Text(stringResource(R.string.pass)) },
                    maxLines = 1,
                    textStyle = BooksTheme.typography.bodySmall.copy(
                        color = AccentLite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        if (passwd.value.isNotEmpty()) {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    tint = AccentLite,
                                    painter = if (passwordVisible) {
                                        painterResource(R.drawable.vector)
                                    } else {
                                        painterResource(R.drawable.eye_on)
                                    },
                                    contentDescription = null
                                )
                            }
                        }
                    },
                )

                Button(
                    onClick = {function.invoke()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        disabledContainerColor = AccentMedium,
                        contentColor = AccentDark,
                        disabledContentColor = AccentLite
                    ),
                    enabled = passwd.value.isNotEmpty() && email.value.isNotEmpty()
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        text = stringResource(R.string.enter),
                        style = BooksTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(openLibraryScreen: () -> Unit) {
    val items = listOf(
        painterResource(R.drawable.image),
        painterResource(R.drawable.image1),
        painterResource(R.drawable.image2),
        painterResource(R.drawable.image3),
        painterResource(R.drawable.image4),
        painterResource(R.drawable.image5),
        painterResource(R.drawable.image6)
    )
    InfiniteCarousel(items = items, openLibraryScreen)
}