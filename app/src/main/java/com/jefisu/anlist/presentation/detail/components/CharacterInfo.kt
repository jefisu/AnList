package com.jefisu.anlist.presentation.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.ui.theme.DavysGrey
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun CharacterInfo(
    character: Character,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(70.dp)
                .width(45.09.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = character.name,
                style = defaultTextStyle,
                color = DavysGrey
            )
            character.voiceActor?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    style = defaultTextStyle,
                    fontSize = 8.sp,
                    color = PhilippineGray
                )
            }
        }
    }
}