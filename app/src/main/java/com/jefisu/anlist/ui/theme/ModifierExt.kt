package com.jefisu.anlist.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale

@Stable
fun Modifier.mirror() = scale(-1f, 1f)