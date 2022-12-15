package com.jefisu.anlist.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomIcon
import com.jefisu.anlist.core.presentation.LoadingGif
import com.jefisu.anlist.core.presentation.RotateIcon
import com.jefisu.anlist.presentation.detail.components.CharacterItem
import com.jefisu.anlist.presentation.detail.components.MainAnimeInfo
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.GraniteGray
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.clickRipple
import com.jefisu.anlist.ui.theme.defaultTextStyle
import com.jefisu.anlist.ui.theme.interFontFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Destination(
    navArgsDelegate = DetailNavArg::class
)
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var showAll by remember { mutableStateOf(false) }
    var showAllStats by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val openYoutubeTrailer = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/watch?v=${state.anime?.trailerYoutubeId}")
        )
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
    val topAlphaColorAnim by animateFloatAsState(
        targetValue = if (visibility > 0f) visibility else 0f
    )

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

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
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.Green)
                ) {
                    /* TODO */
                    Text(
                        text = "Implement this",
                        fontSize = 32.sp
                    )
                }
            }
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
                        .graphicsLayer {
                            alpha = 1f - visibility
                        }
                ) {
                    AsyncImage(
                        model = anime.imageBackground,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Black, Color.Transparent),
                                    endY = 50f
                                )
                            )
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 400f
                                )
                            )
                    )
                }
            }
            LazyColumn(state = lazyState) {
                stickyHeader {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkSlateBlue.copy(topAlphaColorAnim))
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
                            visible = visibility > 0.89f,
                            enter = fadeIn() + expandHorizontally(),
                            exit = fadeOut() + shrinkHorizontally(),
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 32.dp, start = 8.dp)
                        ) {
                            Text(
                                text = anime.title,
                                style = defaultTextStyle,
                                fontSize = 18.sp,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                        AnimatedVisibility(
                            visible = visibility > 0.89f,
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
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
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
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            MainAnimeInfo(
                                value = anime.rate?.run { String.format("%.2f", this) }
                                    ?: "No rating",
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
                        }
                        AnimatedVisibility(
                            visible = showAllStats,
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize()
                            ) {
                                MainAnimeInfo(
                                    value = anime.studios.joinToString(),
                                    info = stringResource(R.string.studios)
                                )
                                MainAnimeInfo(
                                    value = anime.status,
                                    info = stringResource(R.string.status)
                                )
                                Box(
                                    modifier = Modifier
                                        .width(83.dp)
                                        .height(43.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        RotateIcon(
                            icon = Icons.Default.ArrowBackIosNew,
                            isRotated = showAllStats,
                            onClick = { showAllStats = !showAllStats },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.synopsis),
                                style = defaultTextStyle,
                                fontSize = 16.sp,
                                color = DarkSlateBlue,
                            )
                            Text(
                                text = stringResource(R.string.see_all),
                                style = defaultTextStyle,
                                fontSize = 10.sp,
                                color = GraniteGray,
                                modifier = Modifier.clickRipple {
                                    scope.launch { sheetState.show() }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = anime.synopsis,
                            color = GraniteGray,
                            textAlign = TextAlign.Justify,
                            maxLines = 4,
                            fontSize = 12.sp,
                            fontFamily = interFontFamily,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.character),
                                style = defaultTextStyle,
                                fontSize = 16.sp,
                                color = DarkSlateBlue
                            )
                            Text(
                                text = stringResource(R.string.see_all),
                                style = defaultTextStyle,
                                fontSize = 10.sp,
                                color = GraniteGray,
                                modifier = Modifier.clickRipple {
                                    scope.launch { sheetState.show() }
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow {
                        val characters = state.characters.filterIndexed { i, _ -> i < 10 }
                        itemsIndexed(characters) { i, character ->
                            CharacterItem(
                                character = character,
                                modifier = Modifier.padding(
                                    start = if (i == 0) 16.dp else 0.dp,
                                    end = 16.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}