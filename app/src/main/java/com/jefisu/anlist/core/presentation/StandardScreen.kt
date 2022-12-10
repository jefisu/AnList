package com.jefisu.anlist.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jefisu.anlist.R
import com.jefisu.anlist.ui.theme.DarkSlateBlue

@Composable
fun StandardScreen(
    navigationIcon: ImageVector,
    isLoading: Boolean,
    navigationOnClick: () -> Unit,
    title: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(DarkSlateBlue)
            ) {
                IconButton(onClick = navigationOnClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                title()
            }
        }
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingGif(
                    content = R.drawable.pikachu_walking
                )
            }
        } else {
            content(it)
        }
    }
}