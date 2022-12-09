package com.jefisu.anlist.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun AnimeItem(
    anime: Anime,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp),
    space: Dp = 4.dp,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = defaultTextStyle.copy(
        fontSize = 10.sp,
        color = DarkSlateBlue
    )
) {
    val width = 110.dp
    val height = 156.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(width)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
    ) {
        AsyncImage(
            model = anime.poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width, height)
                .clip(shape)
        )
        Spacer(modifier = Modifier.height(space))
        Text(
            text = anime.title,
            style = textStyle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}