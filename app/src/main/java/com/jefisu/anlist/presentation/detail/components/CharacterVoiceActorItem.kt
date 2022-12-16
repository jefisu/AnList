package com.jefisu.anlist.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.R
import com.jefisu.anlist.ui.theme.defaultTextStyle
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun CharacterVoiceActorItem(
    name: String,
    image: String?,
    size: DpSize = DpSize(
        width = 74.dp,
        height = 115.dp
    )
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(8.dp))
    ) {
        GlideImage(
            imageModel = { image },
            component = rememberImageComponent {
                +ShimmerPlugin(
                    baseColor = Color.LightGray,
                    highlightColor = Color.DarkGray
                )
                +PlaceholderPlugin.Failure(painterResource(R.drawable.no_image_available))
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 230f
                    )
                )
        )
        Text(
            text = name,
            style = defaultTextStyle,
            fontSize = 10.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(size.width)
                .padding(4.dp)
                .align(Alignment.BottomStart)
        )
    }
}