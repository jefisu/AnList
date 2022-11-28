package com.jefisu.anlist.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.LightGold
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun MainAnimeInfo(
    value: String,
    info: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (info == "rate") {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(6.dp),
                    tint = LightGold
                )
            }
            Text(
                text = value,
                style = defaultTextStyle,
                color = if (info == "rate") LightGold else DarkSlateBlue

            )
        }
        Text(
            text = info,
            style = defaultTextStyle,
            color = PhilippineGray
        )
    }
}

@Preview
@Composable
fun PreviewMainAnimeInfo() {
    MainAnimeInfo(
        info = "rate",
        value = "8.03"
    )
}