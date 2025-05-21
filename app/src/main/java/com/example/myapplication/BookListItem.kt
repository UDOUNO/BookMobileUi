package com.example.booksapp.presentation.models

import androidx.compose.ui.graphics.painter.Painter

data class BookListItem(
    val title:  String,
    val authors: String,
    val cover: Painter
)