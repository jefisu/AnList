package com.jefisu.anlist.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jefisu.anlist.R
import com.jefisu.anlist.data.dto.jikan_moe.search.SearchResponse
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.mapper.toAnime
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun CustomCard(
    anime: Anime,
    onClick: () -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    size: DpSize = DpSize(211.dp, 119.dp)
) {
    var heightRow by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .clip(shape)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            )
            .then(modifier)
    ) {
        Box {
            AsyncImage(
                model = anime.poster,
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
                        text = anime.rate,
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
                    modifier = Modifier.width(size.width - 24.dp)
                )
                Text(
                    text = anime.studios.joinToString(),
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
    val context = LocalContext.current
    val animeJson = context.resources
        .openRawResource(R.raw.top_airing_anime)
        .readBytes()
        .decodeToString()

    val animeParsed = Json.decodeFromString<SearchResponse>(animeJson)
        .data.map { it.toAnime() }
        .first()

    Box(modifier = Modifier.padding(16.dp)) {
        CustomCard(
            anime = animeParsed,
            onClick = {}
        )
    }
}