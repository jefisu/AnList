package com.jefisu.anlist.presentation.detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomTabs(
    tabs: List<String>,
    characters: List<Character>,
    reviews: List<Review>,
    genres: List<Int>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            contentColor = DarkSlateBlue,
            modifier = modifier
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == pagerState.currentPage,
                    selectedContentColor = DarkSlateBlue,
                    unselectedContentColor = PhilippineGray,
                    onClick = {
                        scope.launch { pagerState.scrollToPage(index) }
                    },
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Text(
                        text = tab,
                        fontSize = defaultTextStyle.fontSize,
                        fontWeight = FontWeight.Medium,
                        fontFamily = defaultTextStyle.fontFamily
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalPager(
            count = tabs.size,
            state = pagerState,
        ) {
            when (tabs[currentPage]) {
                tabs[0] -> {
                    FlowRow(
                        mainAxisSpacing = 16.dp,
                        crossAxisSpacing = 8.dp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        characters.forEachIndexed { index, character ->
                            CharacterInfo(
                                character = character,
                                modifier = Modifier.width(164.dp)
                            )
                        }
                    }
                }
                tabs[1] -> {
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
                tabs[2] -> {
                    FlowRow(
                        mainAxisSpacing = 12.dp,
                        crossAxisSpacing = 12.dp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        genres.forEach {
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
    }
}