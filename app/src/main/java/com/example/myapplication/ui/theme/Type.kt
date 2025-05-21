package com.example.myapplication.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import org.w3c.dom.Text

@Immutable
data class BooksTypography(
    val bookName: TextStyle,
    val title: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val body: TextStyle,
    val bodySmall: TextStyle,
    val footnote: TextStyle,
    val text: TextStyle,
    val quote: TextStyle,
)

val velasansFamily = FontFamily(Font(R.font.velasans_regular, weight = FontWeight.Normal),
    Font(R.font.velasans_bold, weight = FontWeight.Bold))

val alumniSansFamily = FontFamily(Font(R.font.alumnisans_bold, weight = FontWeight.Bold))

val georgiaFamily = FontFamily(
    Font(R.font.georgia, weight = FontWeight.Normal),
    Font(R.font.georgiai, weight = FontWeight.Normal, style = FontStyle.Italic)
)



val Typo = BooksTypography(
    title = TextStyle(
        fontFamily = alumniSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 96.sp,
        lineHeight = 77.sp,
        letterSpacing = 0.sp
    ), h1 = TextStyle(
        fontFamily = alumniSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.sp
    ), h2 = TextStyle(
        fontFamily = alumniSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ), body = TextStyle(
        fontFamily = velasansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.8.sp,
        letterSpacing = 0.sp
    ), bodySmall = TextStyle(
        fontFamily = velasansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.2.sp,
        letterSpacing = 0.sp
    ), footnote = TextStyle(
        fontFamily = velasansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.sp
    ), text = TextStyle(
        fontFamily = georgiaFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ), quote = TextStyle(
        fontFamily = georgiaFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.8.sp,
        letterSpacing = 0.sp
    ), bookName = TextStyle(
        fontFamily = alumniSansFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp
    )
)

val LocalReplacementTypography = staticCompositionLocalOf {
    BooksTypography(
        title = TextStyle.Default,
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        body = TextStyle.Default,
        bodySmall = TextStyle.Default,
        footnote = TextStyle.Default,
        text = TextStyle.Default,
        quote = TextStyle.Default,
        bookName = TextStyle.Default
    )
}
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)