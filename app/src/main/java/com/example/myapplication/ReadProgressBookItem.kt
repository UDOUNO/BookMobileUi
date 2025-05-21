package com.example.booksapp.presentation.models

import androidx.compose.ui.graphics.painter.Painter

data class ReadProgressBookItem(
    val title:  String,
    val part: String,
    val cover: Painter,
    val fraction: Float
)