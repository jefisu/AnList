package com.jefisu.anlist.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jefisu.anlist.R
import com.jefisu.anlist.core.util.UiText
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun ErrorScreen(
    error: UiText
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = R.drawable.pikachu_looking,
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = error.asString(),
            style = defaultTextStyle,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = DarkSlateBlue,
            modifier = Modifier.graphicsLayer {
                translationY = -16.dp.toPx()
            }
        )
    }
}