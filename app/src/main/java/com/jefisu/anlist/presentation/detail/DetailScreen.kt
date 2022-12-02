package com.jefisu.anlist.presentation.detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomIcon
import com.jefisu.anlist.presentation.detail.components.CharacterInfo
import com.jefisu.anlist.presentation.detail.components.MainAnimeInfo
import com.jefisu.anlist.presentation.detail.components.ReviewItem
import com.jefisu.anlist.presentation.detail.components.TabsContent
import com.jefisu.anlist.presentation.detail.util.Anime
import com.jefisu.anlist.presentation.detail.util.Character
import com.jefisu.anlist.presentation.detail.util.Review
import com.jefisu.anlist.presentation.detail.util.getGenresImage
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.GraniteGray
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun DetailScreen(
    anime: Anime
) {
    var showAll by remember { mutableStateOf(false) }
    val rotateIconAnim by animateFloatAsState(
        targetValue = if (showAll) 90f else 270f,
        animationSpec = tween(400)
    )

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(id = anime.imageBackground),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                Color.Black.copy(0.3f),
                blendMode = BlendMode.Luminosity
            ),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(228.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
        )
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = Color.White
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            CustomIcon(
                icon = R.drawable.ic_arrow_forward,
                text = stringResource(R.string.watch_trailer),
                size = 24.dp,
                shape = CircleShape,
                color = Color.Black.copy(0.5f),
                textStyle = defaultTextStyle.copy(
                    fontSize = 14.sp, color = Color.White
                ),
                modifier = Modifier.padding(top = 36.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(anime.poster),
                contentDescription = null,
                modifier = Modifier
                    .height(202.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = anime.name,
                style = defaultTextStyle,
                fontSize = 18.sp,
                color = DarkSlateBlue,
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = anime.episodeDuration,
                style = defaultTextStyle,
                fontSize = 14.sp,
                color = PhilippineGray
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
            ) {
                MainAnimeInfo(
                    value = anime.rate.toString(),
                    info = stringResource(R.string.rate)
                )
                MainAnimeInfo(
                    value = anime.eps.toString(),
                    info = stringResource(R.string.eps)
                )
                MainAnimeInfo(
                    value = anime.premiered,
                    info = stringResource(R.string.premiered)
                )
                MainAnimeInfo(
                    value = anime.studio,
                    info = stringResource(R.string.studio)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.synopsis),
                style = defaultTextStyle,
                color = DarkSlateBlue,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = anime.synopsis,
                fontSize = 10.sp,
                color = GraniteGray,
                lineHeight = 14.sp,
                textAlign = TextAlign.Justify,
                maxLines = if (showAll) Int.MAX_VALUE else 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.animateContentSize()
            )
            Spacer(modifier = Modifier.height(2.dp))
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = GraniteGray,
                modifier = Modifier
                    .size(16.dp)
                    .graphicsLayer(rotationZ = rotateIconAnim)
                    .clickable { showAll = !showAll }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TabsContent(
                pagerState = rememberPagerState(),
                scope = rememberCoroutineScope(),
                tabsName = listOf(
                    stringResource(R.string.character),
                    stringResource(R.string.review),
                    stringResource(R.string.genre)
                ),
                modifier = Modifier.fillMaxWidth(),
                content = { tab ->
                    when (tab) {
                        stringResource(R.string.character) -> {
                            FlowRow(
                                mainAxisSpacing = 16.dp,
                                crossAxisSpacing = 8.dp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                anime.characters.forEach { character ->
                                    CharacterInfo(
                                        character = character,
                                        modifier = Modifier.width(164.dp)
                                    )
                                }
                            }
                        }
                        stringResource(R.string.review) -> {
                            Column {
                                anime.reviews.forEach {
                                    ReviewItem(
                                        review = it,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .animateContentSize()
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                }
                            }
                        }
                        stringResource(R.string.genre) -> {
                            FlowRow(
                                mainAxisSpacing = 12.dp,
                                crossAxisSpacing = 12.dp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            ) {
                                getGenresImage(anime.genres).forEach {
                                    Image(
                                        painter = painterResource(it),
                                        contentDescription = null,
                                        modifier = Modifier.height(94.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        anime = Anime(
            name = "Naruto",
            rate = 7.53f,
            eps = 24,
            episodeDuration = "24 min",
            premiered = "Fall 2022",
            studio = "Pierrot",
            characters = listOf(
                Character("Naruto Uzumaki", "Junko Takeuchi", R.drawable.naruto),
                Character("Shikamaru Nara", "Nobutoshi Canna", R.drawable.shikamaru),
                Character("Hinata Hyuuga", "Nana Mizuki", R.drawable.hinata),
                Character("Kakashi Hatake", "Kazuhiko Inoue", R.drawable.kakashi),
            ),
            reviews = listOf(
                Review(
                    "jefisu",
                    LoremIpsum(42).values.first(),
                    "11/25/2022",
                    R.drawable.jefisu_profile
                ),
                Review(
                    "jefisu",
                    LoremIpsum(42).values.first(),
                    "11/25/2022",
                    R.drawable.jefisu_profile
                ),
                Review(
                    "jefisu",
                    LoremIpsum(42).values.first(),
                    "11/25/2022",
                    R.drawable.jefisu_profile
                ),
                Review(
                    "jefisu",
                    LoremIpsum(42).values.first(),
                    "11/25/2022",
                    R.drawable.jefisu_profile
                ),
            ),
            genres = listOf("Action", "Adventure", "Fantasy"),
            imageBackground = R.drawable.naruto_background,
            poster = R.drawable.naruto_poster,
            synopsis = "Demons that once almost destroyed the world, are revived by someone. To prevent the" +
                " world from being destroyed, the demon has to be sealed and the only one who can do it" +
                " is the shrine maiden Shion from the country of demons, who has two powers; one is sealing" +
                " demons and the other is predicting the deaths of humans. This time Naruto's mission is to" +
                " guard Shion, but she predicts Naruto's death. The only way to escape it, is to get away from Shion," +
                " which would leave her unguarded, then the demon, whose only goal is to kill Shion will do so, thus" +
                " meaning the end of the world. Naruto decides to challenge this \\\"prediction of death,\\\" but fails to " +
                "prove Shion's prediction wrong and supposedly dies in vain.\\n(Source: Wikipedia)",
        )
    )
}