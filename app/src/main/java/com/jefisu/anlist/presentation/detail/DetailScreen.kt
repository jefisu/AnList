package com.jefisu.anlist.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomIcon
import com.jefisu.anlist.core.presentation.LoadingGif
import com.jefisu.anlist.presentation.detail.components.CustomTabs
import com.jefisu.anlist.presentation.detail.components.MainAnimeInfo
import com.jefisu.anlist.presentation.detail.util.getGenresImage
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.GraniteGray
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Destination(
    navArgsDelegate = DetailNavArg::class
)
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val reviews = state.reviews
    val characters = state.characters

    val context = LocalContext.current
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
    val openYoutubeTrailer = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/watch?v=${state.anime?.trailerYoutubeId}")
        )
    }

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingGif(
                content = R.drawable.pikachu_walking
            )
        }
    }

    state.anime?.let { anime ->
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
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
                )
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
                        ),
                        onClick = {
                            context.startActivity(openYoutubeTrailer)
                        }
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = anime.title,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .pin()
                ) {
                    IconButton(
                        onClick = navigator::navigateUp
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    AnimatedVisibility(
                        visible = collapsingState.toolbarState.progress < 0.89f,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = anime.title,
                            style = defaultTextStyle,
                            fontSize = 18.sp,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    AnimatedVisibility(
                        visible = collapsingState.toolbarState.progress < 0.89f,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally(),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        IconButton(
                            onClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, state.anime?.malUrl)
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            }
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
                        value = anime.rate?.run { String.format("%.2f", this) } ?: "No rating",
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
                CustomTabs(
                    tabs = stringArrayResource(R.array.tabs).asList(),
                    characters = characters,
                    reviews = reviews,
                    genres = getGenresImage(anime.genres)
                )
            }
        }
    }
}