package com.jefisu.anlist.presentation.detail.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.jefisu.anlist.ui.theme.ChineseWhite
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    pagerState: PagerState,
    scope: CoroutineScope,
    tabsName: List<String>,
    content: @Composable (Int, List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TabsDetails(
            pagerState = pagerState,
            scope = scope,
            tabsName = tabsName
        )
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalPager(
            count = tabsName.size,
            state = pagerState,
            content = { content(currentPage, tabsName) }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsDetails(
    pagerState: PagerState,
    scope: CoroutineScope,
    tabsName: List<String>,
    modifier: Modifier = Modifier,
    dividerColor: Color = ChineseWhite
) {
    Box(
        modifier = modifier
    ) {
        Divider(
            color = dividerColor,
            modifier = Modifier.align(Alignment.BottomStart)
        )
        Row {
            tabsName.forEachIndexed { index, tab ->
                CustomTab(
                    selected = pagerState.currentPage == index,
                    text = tab,
                    selectedColor = DarkSlateBlue,
                    unselectedColor = PhilippineGray,
                    dividerUnselectedColor = dividerColor,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            scope.launch { pagerState.scrollToPage(index) }
                        }
                )
            }
        }
    }
}

@Composable
private fun CustomTab(
    selected: Boolean,
    text: String,
    selectedColor: Color,
    unselectedColor: Color,
    dividerUnselectedColor: Color,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = selected, label = null)
    val textColor by transition.animateColor(
        label = "",
        targetValueByState = { if (it) selectedColor else unselectedColor },
        transitionSpec = {
            if (false isTransitioningTo true) {
                tween(
                    durationMillis = 150,
                    delayMillis = 100,
                    easing = LinearEasing
                )
            } else {
                tween(
                    durationMillis = 100,
                    easing = LinearEasing
                )
            }
        }
    )
    val dividerColor by transition.animateColor(
        label = "",
        targetValueByState = { if (it) selectedColor else dividerUnselectedColor },
        transitionSpec = {
            if (false isTransitioningTo true) {
                tween(
                    durationMillis = 150,
                    delayMillis = 100,
                    easing = LinearEasing
                )
            } else {
                tween(
                    durationMillis = 100,
                    easing = LinearEasing
                )
            }
        }
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = defaultTextStyle,
            color = textColor
        )
        Divider(
            modifier = Modifier.width(40.dp),
            color = dividerColor
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewTabsDetails() {
    TabsContent(
        pagerState = rememberPagerState(),
        scope = rememberCoroutineScope(),
        tabsName = listOf("Character", "Reviews", "Genre"),
        modifier = Modifier.fillMaxWidth(),
        content = { _, _ -> }
    )
}