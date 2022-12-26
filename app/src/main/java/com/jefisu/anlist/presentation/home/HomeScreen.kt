package com.jefisu.anlist.presentation.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.jefisu.anlist.data.AnimeConstants
import com.jefisu.anlist.presentation.destinations.DetailScreenDestination
import com.jefisu.anlist.presentation.destinations.SearchScreenDestination
import com.jefisu.anlist.presentation.destinations.SeasonScreenDestination
import com.jefisu.anlist.presentation.home.components.AnimeByGenreItem
import com.jefisu.anlist.presentation.home.util.IconSeasonSettings
import com.jefisu.anlist.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
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

    val lazyState = rememberLazyListState()
    val visibility by remember {
        derivedStateOf {
            val isVisibleItems = lazyState.layoutInfo.visibleItemsInfo.isNotEmpty()
            val firstVisibleItem = lazyState.firstVisibleItemIndex == 0

            if (isVisibleItems && firstVisibleItem) {
                val imageSize = lazyState.layoutInfo.visibleItemsInfo[0].size
                val scrollOffset = lazyState.firstVisibleItemScrollOffset
                scrollOffset / imageSize.toFloat()
            } else 1f
        }
    }
    if(!state.isLoading && state.topAiringAnime.isNotEmpty() && state.animes.isNotEmpty()) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .graphicsLayer {
                        alpha = 1f - visibility
                    }
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
            LazyColumn(state = lazyState) {
                stickyHeader {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkSlateBlue.copy(visibility))
                    ) {
                        Text(
                            text = stringResource(R.string.app_name),
                            style = defaultTextStyle,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp)
                        )
                        AnimatedVisibility(visible = visibility ==1f) {
                            IconButton(
                                onClick = { navigator.navigate(SearchScreenDestination()) }
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
                }
                item {
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
                        items(state.topAiringAnime) {
                            CustomCard(
                                anime = it,
                                onClick = {
                                    navigator.navigate(DetailScreenDestination(it.malId))
                                },
                                paddingValues = PaddingValues(
                                    start = if (it == state.topAiringAnime.first()) 16.dp else 0.dp,
                                    end = 12.dp
                                )
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.upcoming_year, LocalDate.now().year),
                        style = defaultTextStyle,
                        fontSize = 14.sp,
                        color = DarkSlateBlue,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        val iconsSeason = listOf(
                            IconSeasonSettings.Fall,
                            IconSeasonSettings.Winter,
                            IconSeasonSettings.Spring,
                            IconSeasonSettings.Summer,
                            IconSeasonSettings.Schedule
                        )
                        iconsSeason.forEach {
                            CustomIcon(
                                icon = it.icon,
                                text = it.title,
                                onClick = {
                                    if (it.title == "Schedule") {
                                        Toast.makeText(
                                            context,
                                            "Is not implemented",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        return@CustomIcon
                                    }
                                    navigator.navigate(
                                        SeasonScreenDestination(
                                            LocalDate.now().year,
                                            it.title
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    AnimeConstants.genres
                        .filter { genre -> state.animes.any { it.genres.contains(genre) } }
                        .forEach { genre ->
                            AnimeByGenreItem(
                                genre = genre,
                                allAnime = state.animes,
                                onNavigate = navigator::navigate
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                }
            }
        }
    }
}