package com.jefisu.anlist.presentation.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomIcon
import com.jefisu.anlist.data.dto.jikan_moe.character.AnimeCharacters
import com.jefisu.anlist.data.dto.jikan_moe.review.AnimeReviews
import com.jefisu.anlist.data.dto.jikan_moe.search.AnimeDto
import com.jefisu.anlist.data.dto.jikan_moe.search.SearchResponse
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.domain.model.mapper.toAnime
import com.jefisu.anlist.domain.model.mapper.toCharacter
import com.jefisu.anlist.domain.model.mapper.toReview
import com.jefisu.anlist.presentation.detail.components.CharacterInfo
import com.jefisu.anlist.presentation.detail.components.MainAnimeInfo
import com.jefisu.anlist.presentation.detail.components.ReviewItem
import com.jefisu.anlist.presentation.detail.components.TabsContent
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.GraniteGray
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun DetailScreen(
    anime: Anime,
    characters: List<Character>,
    reviews: List<Review>,
) {
    var showAll by remember { mutableStateOf(false) }
    var showAllStats by remember { mutableStateOf(false) }
    val rotateIconSynopsisAnim by animateFloatAsState(
        targetValue = if (showAll) 90f else 270f,
        animationSpec = tween(400)
    )
    val rotateIconStatsAnim by animateFloatAsState(
        targetValue = if (showAllStats) 90f else 270f,
        animationSpec = tween(400)
    )

    val scrollState = rememberScrollState()
    val collapsingState = rememberCollapsingToolbarScaffoldState()
    val transition = updateTransition(collapsingState.toolbarState.progress, label = "")
    val alphaAnim by transition.animateFloat(label = "") { progress ->
        if (progress < 0.54f) 0f else 1f
    }
    val alphaBoxAnim by transition.animateFloat(label = "") { progress ->
        if (progress == 0f) 1f else 0f
    }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = collapsingState,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .pin()
                    .background(DarkSlateBlue)
                    .alpha(alphaBoxAnim)
            )
            AsyncImage(
                model = anime.imageBackground,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black.copy(0.3f), BlendMode.Luminosity),
                alpha = if (collapsingState.toolbarState.progress < 0.1f) 0f else 1f,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(228.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
            )

           /* Image(
                painter = painterResource(id = anime.imageBackground),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black.copy(0.3f), BlendMode.Luminosity),
                contentScale = ContentScale.FillWidth,
                alpha = if (collapsingState.toolbarState.progress < 0.1f) 0f else 1f,
                modifier = Modifier
                    .height(228.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
            )*/
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 36.dp)
                    .alpha(alphaAnim)
                    .road(Alignment.TopCenter, Alignment.TopCenter)
            ) {
                CustomIcon(
                    icon = R.drawable.ic_arrow_forward,
                    text = stringResource(R.string.watch_trailer),
                    size = 24.dp,
                    shape = CircleShape,
                    color = Color.Black.copy(0.5f),
                    textStyle = defaultTextStyle.copy(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                AsyncImage(
                    model = anime.poster,
                    contentDescription = null,
                    modifier = Modifier
                        .height(202.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.background,
                            shape = RoundedCornerShape(12.dp)
                        )
                )

                /*Image(
                    painter = painterResource(anime.poster),
                    contentDescription = null,
                    modifier = Modifier
                        .height(202.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.background,
                            shape = RoundedCornerShape(12.dp)
                        )
                )*/
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
                    text = anime.duration,
                    style = defaultTextStyle,
                    fontSize = 14.sp,
                    color = PhilippineGray
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .pin()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { /* TODO */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    AnimatedVisibility(
                        visible = collapsingState.toolbarState.progress < 0.89f
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = anime.name,
                            style = defaultTextStyle,
                            fontSize = 18.sp,
                            color = Color.White,
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                AnimatedVisibility(
                    visible = collapsingState.toolbarState.progress < 0.89f,
                    enter = fadeIn() + expandHorizontally(),
                    exit = fadeOut() + shrinkHorizontally(),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 8.dp)
                ) {
                    IconButton(
                        onClick = { /* TODO */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 12.dp)
                .verticalScroll(scrollState)
        ) {
            FlowRow(
                mainAxisSpacing = 48.dp,
                crossAxisSpacing = 12.dp,
                modifier = Modifier.animateContentSize()
            ) {
                MainAnimeInfo(
                    value = anime.rate.toString(),
                    info = stringResource(R.string.rate)
                )
                MainAnimeInfo(
                    value = "${anime.episodes}",
                    info = stringResource(R.string.eps)
                )
                MainAnimeInfo(
                    value = anime.premiered,
                    info = stringResource(R.string.premiered)
                )
                if (showAllStats) {
                    MainAnimeInfo(
                        value = anime.studios.joinToString(),
                        info = stringResource(R.string.studios)
                    )
                    MainAnimeInfo(
                        value = anime.status,
                        info = stringResource(R.string.status)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = GraniteGray,
                modifier = Modifier
                    .size(16.dp)
                    .graphicsLayer(rotationZ = rotateIconStatsAnim)
                    .clickable { showAllStats = !showAllStats }
            )
            Spacer(modifier = Modifier.height(4.dp))
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
            Spacer(modifier = Modifier.height(4.dp))
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = GraniteGray,
                modifier = Modifier
                    .size(16.dp)
                    .graphicsLayer(rotationZ = rotateIconSynopsisAnim)
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
                                characters.forEach { character ->
                                    CharacterInfo(
                                        character = character,
                                        modifier = Modifier.width(164.dp)
                                    )
                                }
                            }
                        }
                        stringResource(R.string.review) -> {
                            Column {
                                reviews.forEach {
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
                                anime.genres.forEach {
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
    val context = LocalContext.current
    val animeJson = context.resources
        .openRawResource(R.raw.detail_anime)
        .readBytes()
        .decodeToString()
    val charactersJson = context.resources
        .openRawResource(R.raw.characters_anime)
        .readBytes()
        .decodeToString()
    val reviewsJson = context.resources
        .openRawResource(R.raw.reviews_anime)
        .readBytes()
        .decodeToString()

    val animeParsed = Json
        .decodeFromString<SearchResponse<AnimeDto>>(animeJson)
        .data.map { it.toAnime() }
        .first()
    val charactersParsed = Json
        .decodeFromString<AnimeCharacters>(charactersJson)
        .data.map { it.toCharacter() }
    val reviewsParsed = Json
        .decodeFromString<AnimeReviews>(reviewsJson)
        .data.map { it.toReview() }

    DetailScreen(
        anime = animeParsed.copy(imageBackground = "https://media.kitsu.io/anime/cover_images/11/original.jpg"),
        characters = charactersParsed,
        reviews = reviewsParsed
    )
}