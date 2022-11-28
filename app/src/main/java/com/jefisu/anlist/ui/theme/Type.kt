package com.jefisu.anlist.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.R

val interFontFamily = FontFamily(
    Font(R.font.inter_medium),
    Font(R.font.inter_black),
    Font(R.font.inter_bold),
    Font(R.font.inter_light),
    Font(R.font.inter_extrabold),
    Font(R.font.inter_extralight),
    Font(R.font.inter_regular),
    Font(R.font.inter_thin)
)

val defaultTextStyle = TextStyle(
    fontSize = 12.sp,
    color = Color.White,
    fontFamily = interFontFamily,
    fontWeight = FontWeight.Medium
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)