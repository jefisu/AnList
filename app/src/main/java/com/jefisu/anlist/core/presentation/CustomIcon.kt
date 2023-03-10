package com.jefisu.anlist.core.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.Lavender
import com.jefisu.anlist.ui.theme.clickRipple
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun CustomIcon(
    @DrawableRes icon: Int,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Lavender,
    shape: Shape = RoundedCornerShape(12.dp),
    space: Dp = 8.dp,
    paddingValues: PaddingValues = PaddingValues(8.dp),
    size: Dp = 27.dp,
    textStyle: TextStyle = defaultTextStyle.copy(
        fontSize = 10.sp,
        color = DarkSlateBlue
    ),
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .clip(shape)
                .background(color)
                .clickRipple { onClick() }
                .padding(paddingValues)
                .size(size)
        )
        Text(
            text = text,
            style = textStyle
        )
    }
}