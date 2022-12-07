package com.jefisu.anlist.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomIcon
import com.jefisu.anlist.presentation.destinations.DetailScreenDestination
import com.jefisu.anlist.presentation.home.components.CustomCard
import com.jefisu.anlist.presentation.home.util.IconSeasonSettings
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.Gray
import com.jefisu.anlist.ui.theme.Liberty
import com.jefisu.anlist.ui.theme.Platinum
import com.jefisu.anlist.ui.theme.defaultTextStyle
import com.jefisu.anlist.ui.theme.mirror
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import kotlinx.coroutines.launch
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
    val recommendations = state.recommendationsAnime

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
        targetValue = if (collapsingState.toolbarState.progress == 0f) 0.dp else 24.dp
    )
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = state.isLoading && topAirings.isNotEmpty() && recommendations.isNotEmpty()
    )
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            AnimatedVisibility(visible = collapsingState.toolbarState.progress == 0f) {
                FloatingActionButton(
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = DarkSlateBlue.copy(0.95f),
                    contentColor = Color.White,
                    onClick = {
                        scope.launch {
                            collapsingState.toolbarState.expand()
                            scrollState.animateScrollTo(0)
                        }
                    },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        SwipeRefresh(
            state = refreshState,
            onRefresh = viewModel::loadingData
        ) {
            CollapsingToolbarScaffold(
                state = collapsingState,
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
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
                        Column(modifier = Modifier.padding(start = 24.dp)) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(294.75.dp)
                                    .height(53.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Platinum)
                                    .clickable {
                                        scope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                "Not implemented",
                                                "OK"
                                            )
                                        }
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
                        Spacer(modifier = Modifier.height(4.dp))
                        LazyRow {
                            items(topAirings) {
                                CustomCard(
                                    anime = it,
                                    onClick = {
                                        navigator.navigate(DetailScreenDestination(it.malId))
                                    },
                                    paddingValues = PaddingValues(
                                        start = if (it == topAirings.first()) 24.dp else 0.dp,
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
                            .padding(start = 24.dp)
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
                                    scope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            "Not implemented",
                                            "OK"
                                        )
                                    }
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
                            onClick = {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        "Not implemented",
                                        "OK"
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Sort,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.mirror()
                            )
                        }
                    }
                    Box(modifier = Modifier.height(260.dp))
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = paddingAnim)
                        .verticalScroll(scrollState)
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
                                onClick = {
                                    scope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            "Not implemented",
                                            "OK"
                                        )
                                    }
                                }
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
                    FlowRow(
                        mainAxisSpacing = 16.dp,
                        crossAxisSpacing = 16.dp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        recommendations.forEach {
                            AsyncImage(
                                model = it.image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .size(164.dp, 246.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(),
                                        onClick = {
                                            navigator.navigate(DetailScreenDestination(it.malId))
                                        }
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val context = LocalContext.current
    val recommendations = buildList {
        Json.decodeFromString<RecommendationsResponse>(
            context.resources
                .openRawResource(R.raw.recommendations_anime)
                .readBytes()
                .decodeToString()
        ).recommendations.forEach { recommendationPerUser ->
            addAll(recommendationPerUser.entry.map { it.toRecommendation() })
        }
    }

    val topAirings = Json.decodeFromString<SearchResponse<AnimeDto>>(
        context.resources
            .openRawResource(R.raw.top_airing_anime)
            .readBytes()
            .decodeToString()
    ).data.map { it.toAnime() }
        .filterIndexed { i, _ -> i < 5 }

    HomeScreen(
        recommendations = recommendations,
        topAirings = topAirings.toMutableList().apply {
            replaceAll { it.copy(imageBackground = "https://media.kitsu.io/anime/poster_images/818/original.jpg") }
        }
   )
}*/