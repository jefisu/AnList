package com.jefisu.anlist.presentation.home

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomCard
import com.jefisu.anlist.core.presentation.CustomIcon
import com.jefisu.anlist.core.presentation.GradientProgressbar
import com.jefisu.anlist.core.presentation.LoadingGif
import com.jefisu.anlist.presentation.destinations.DetailScreenDestination
import com.jefisu.anlist.presentation.destinations.SearchScreenDestination
import com.jefisu.anlist.presentation.destinations.SeasonScreenDestination
import com.jefisu.anlist.presentation.home.components.AnimeItem
import com.jefisu.anlist.presentation.home.util.IconSeasonSettings
import com.jefisu.anlist.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalToolbarApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val topAirings = state.topAiringAnime
    val animesByGenre = state.animesByGenre

    val iconsSeason = listOf(
        IconSeasonSettings.Fall,
        IconSeasonSettings.Winter,
        IconSeasonSettings.Spring,
        IconSeasonSettings.Summer,
        IconSeasonSettings.Schedule
    )
    val collapsingState = rememberCollapsingToolbarScaffoldState()
    val alphaAnim by animateFloatAsState(
        targetValue = collapsingState.toolbarState.progress
    )
    val paddingAnim by animateDpAsState(
        targetValue = if (collapsingState.toolbarState.progress == 0f) 0.dp else 16.dp
    )
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoadingGif(
                content = R.drawable.pikachu_walking
            )
            Spacer(modifier = Modifier.height(16.dp))
            GradientProgressbar(
                progress = state.progress,
                indicatorPadding = 60.dp
            )
        }
    }

    AnimatedVisibility(
        visible = !state.isLoading && state.topAiringAnime.isNotEmpty(),
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 1000))
    ) {
        CollapsingToolbarScaffold(
            state = collapsingState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            modifier = Modifier.fillMaxSize(),
            toolbar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp))
                        .background(DarkSlateBlue)
                        .pin()
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_object),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Liberty),
                        modifier = Modifier
                            .width(291.04.dp)
                            .height(95.dp)
                            .align(Alignment.TopEnd)
                            .alpha(alphaAnim)
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
                Column(
                    modifier = Modifier
                        .padding(top = 48.dp)
                        .road(Alignment.TopStart, Alignment.TopStart)
                ) {
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(294.75.dp)
                                .height(53.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Platinum)
                                .clickRipple {
                                    navigator.navigate(SearchScreenDestination())
                                }
                                .padding(16.dp)
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = Gray,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.search_anime_hint),
                                    style = defaultTextStyle,
                                    fontSize = 14.sp,
                                    color = Gray,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.top_airing, LocalDate.now().year),
                            style = defaultTextStyle,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        items(topAirings) {
                            CustomCard(
                                anime = it,
                                onClick = {
                                    navigator.navigate(DetailScreenDestination(it.malId))
                                },
                                paddingValues = PaddingValues(
                                    start = if (it == topAirings.first()) 16.dp else 0.dp,
                                    end = 12.dp
                                )
                            )
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = defaultTextStyle,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.weight(1f)
                    )
                    AnimatedVisibility(visible = collapsingState.toolbarState.progress < 0.15f) {
                        IconButton(
                            onClick = {
                                navigator.navigate(SearchScreenDestination())
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.mirror()
                            )
                        }
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.mirror()
                        )
                    }
                }
                Box(modifier = Modifier.height(264.dp))
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(top = paddingAnim)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = stringResource(R.string.upcoming_year, LocalDate.now().year),
                    style = defaultTextStyle,
                    fontSize = 14.sp,
                    color = DarkSlateBlue,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    iconsSeason.forEach {
                        CustomIcon(
                            icon = it.icon,
                            text = it.title,
                            onClick = {
                                if (it.title == "Schedule") {
                                    Toast.makeText(context, "Is not implemented", Toast.LENGTH_SHORT).show()
                                    return@CustomIcon
                                }
                                navigator.navigate(SeasonScreenDestination(LocalDate.now().year, it.title))
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                animesByGenre.onEachIndexed { index, (genre, animes) ->
                    if (index in 0..5) {
                        Text(
                            text = genre,
                            style = defaultTextStyle,
                            fontSize = 14.sp,
                            color = DarkSlateBlue,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        LazyRow {
                            itemsIndexed(animes) { index, anime ->
                                AnimeItem(
                                    anime = anime,
                                    onClick = {
                                        navigator.navigate(DetailScreenDestination(anime.malId))
                                    },
                                    modifier = Modifier.padding(
                                        start = if (index == 0) 16.dp else 0.dp
                                    )
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}