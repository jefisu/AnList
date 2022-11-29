package com.jefisu.anlist.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.R
import com.jefisu.anlist.presentation.detail.util.Anime
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun CustomCard(
    anime: Anime,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    size: DpSize = DpSize(211.dp, 119.dp)
) {
    var heightRow by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(
        modifier = modifier.clip(shape)
    ) {
        Box {
            Image(
                painter = painterResource(anime.poster),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(size)
            )
            Box(
                modifier = Modifier
                    .size(size)
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
                        text = "${anime.rate}",
                        style = defaultTextStyle,
                        fontSize = 12.sp,
                        color = Color.Yellow.copy(0.8f)
                    )
                }
                Text(
                    text = anime.name,
                    style = defaultTextStyle,
                )
                Text(
                    text = anime.studio,
                    style = defaultTextStyle,
                    fontSize = 10.sp,
                    color = PhilippineGray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomCard() {
    Box(modifier = Modifier.padding(16.dp)) {
        CustomCard(
            anime = Anime(
                name = "Classroom of the elite",
                rate = 8.86f,
                studio = "Lerche",
                poster = R.drawable.classroom_of_the_elite,
                eps = 0,
                synopsis = "",
                episodeDuration = "",
                premiered = "",
                characters = emptyList(),
                reviews = emptyList(),
                genres = emptyList(),
                imageBackground = 0
            )
        )
    }
}