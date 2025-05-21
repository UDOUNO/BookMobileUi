package com.example.booksapp.presentation.common.ui_elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.AccentDark
import com.example.myapplication.ui.theme.AccentMedium

@Composable
fun ReadProgressIndicator(
    modifier: Modifier = Modifier,
    progressFraction: Float
) {
    Row(
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .padding(end = 2.dp)
                .fillMaxWidth(progressFraction)
                .height(4.dp),
            shape = RoundedCornerShape(8.dp),
            color = AccentDark
        ) {}
        Box {
            Surface(
                modifier = Modifier
                    .padding(start = 2.dp)
                    .fillMaxWidth()
                    .height(4.dp)
                    .align(Alignment.CenterEnd),
                shape = RoundedCornerShape(8.dp),
                color = AccentMedium
            ) {}
            Surface(
                modifier = Modifier
                    .size(4.dp)
                    .align(Alignment.CenterEnd),
                shape = CircleShape,
                color = AccentDark
            ) {}
        }
    }
}