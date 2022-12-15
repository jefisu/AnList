package com.jefisu.anlist.core.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jefisu.anlist.ui.theme.GraniteGray
import com.jefisu.anlist.ui.theme.clickRipple

@Composable
fun RotateIcon(
    icon: ImageVector,
    isRotated: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = GraniteGray
) {
    val rotateAnim by animateFloatAsState(
        targetValue = if (isRotated) 90f else 270f,
        animationSpec = tween(400)
    )
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .size(20.dp)
            .graphicsLayer(rotationZ = rotateAnim)
            .clickRipple { onClick() }
            .then(modifier)
    )
}