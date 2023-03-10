package com.jefisu.anlist.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.presentation.destinations.DetailScreenDestination
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.clickRipple
import com.jefisu.anlist.ui.theme.defaultTextStyle
import com.ramcosta.composedestinations.spec.Direction

@Composable
fun CustomCard(
    anime: Anime,
    onClick: (Direction) -> Unit,
    imageModifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    shape: Shape = RoundedCornerShape(8.dp)
) {
    var heightRow by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .clip(shape)
            .clickRipple { onClick(DetailScreenDestination(anime.malId)) }
    ) {
        Box {
            AsyncImage(
                model = anime.poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(0.9f))
                        )
                    )
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .onSizeChanged {
                    with(density) { heightRow = it.height.toDp() }
                }
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(heightRow)
                    .background(DarkSlateBlue)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow.copy(0.8f),
                        modifier = Modifier.size(8.dp)
                    )
                    Text(
                        text = anime.rate?.run { String.format("%.2f", this) } ?: "No rating",
                        style = defaultTextStyle,
                        fontSize = 12.sp,
                        color = Color.Yellow.copy(0.8f)
                    )
                }
                Text(
                    text = anime.title,
                    style = defaultTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text(
                    text = anime.studios.joinToString().ifBlank { "Unidentified studio" },
                    style = defaultTextStyle,
                    fontSize = 10.sp,
                    color = PhilippineGray,
                    maxLines = 1,
                    modifier = Modifier.padding(end = 12.dp)
                )
            }
        }
    }
}