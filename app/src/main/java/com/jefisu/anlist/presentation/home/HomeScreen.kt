package com.jefisu.anlist.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomIcon
import com.jefisu.anlist.core.util.isOdd
import com.jefisu.anlist.presentation.detail.util.Anime
import com.jefisu.anlist.presentation.home.components.CustomCard
import com.jefisu.anlist.presentation.home.util.IconSeasonSettings
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.Gray
import com.jefisu.anlist.ui.theme.Liberty
import com.jefisu.anlist.ui.theme.Platinum
import com.jefisu.anlist.ui.theme.defaultTextStyle
import com.jefisu.anlist.ui.theme.mirror
import java.time.LocalDate

@Composable
fun HomeScreen(
    topAirings: List<Anime>,
    recommends: List<Int>,
) {
    val iconsSeason = listOf(
        IconSeasonSettings.Fall,
        IconSeasonSettings.Winter,
        IconSeasonSettings.Spring,
        IconSeasonSettings.Summer,
        IconSeasonSettings.Schedule
    )
    var text by remember { mutableStateOf("") }

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(bottomStart = 24.dp))
                .background(DarkSlateBlue)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_object),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Liberty),
                modifier = Modifier
                    .width(291.04.dp)
                    .height(95.dp)
                    .align(Alignment.TopEnd)
                    .graphicsLayer {
                        translationY = -4.dp.toPx()
                        translationX = 20.dp.toPx()
                    }
            )
            Image(
                painter = painterResource(R.drawable.ic_custom_circle),
                contentDescription = null,
                modifier = Modifier
                    .size(149.dp)
                    .align(Alignment.BottomEnd)
                    .graphicsLayer {
                        translationY = 53.dp.toPx()
                        translationX = 17.dp.toPx()
                    }
            )
        }
        Column {
            Column(
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, end = 12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = defaultTextStyle,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.mirror()
                    )
                }
                Spacer(modifier = Modifier.height(13.dp))
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = defaultTextStyle.copy(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_anime_hint),
                            style = defaultTextStyle,
                            fontSize = 14.sp,
                            color = Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Platinum,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(294.75.dp)
                        .height(53.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.top_airing, LocalDate.now().year),
                    style = defaultTextStyle,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow {
                items(topAirings) {
                    CustomCard(
                        anime = it,
                        modifier = Modifier.padding(
                            start = if (it == topAirings.first()) 24.dp else 0.dp
                        )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.upcoming_year, LocalDate.now().year),
                    style = defaultTextStyle,
                    fontSize = 14.sp,
                    color = DarkSlateBlue
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    iconsSeason.forEach {
                        CustomIcon(
                            icon = it.icon,
                            text = it.title,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.recommend),
                    style = defaultTextStyle,
                    fontSize = 14.sp,
                    color = DarkSlateBlue
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
//                    userScrollEnabled = false
                ) {
                    items(recommends) {
                        Image(
                            painter = painterResource(it),
                            contentDescription = null,
                            modifier = Modifier
                                .size(164.dp, 246.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                    if (isOdd(recommends.size)) {
                        item {
                            Box(modifier = Modifier)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(0.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        topAirings = listOf(
            Anime(
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
            ),
            Anime(
                name = "Boku no Hero Academia",
                rate = 8.14f,
                studio = "Bones",
                poster = R.drawable.boku_no_hero,
                eps = 0,
                synopsis = "",
                episodeDuration = "",
                premiered = "",
                characters = emptyList(),
                reviews = emptyList(),
                genres = emptyList(),
                imageBackground = 0
            )
        ),
        recommends = listOf(
            R.drawable.pokemon,
            R.drawable.one_piece,
            R.drawable.mahouka,
            R.drawable.haikyuu
        )
    )
}