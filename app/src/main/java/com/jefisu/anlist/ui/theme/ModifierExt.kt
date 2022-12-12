package com.jefisu.anlist.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale

@Stable
fun Modifier.mirror() = scale(-1f, 1f)

@Stable
@Composable
fun Modifier.clickRipple(onClick: () -> Unit) = clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = rememberRipple(),
    onClick = onClick
)