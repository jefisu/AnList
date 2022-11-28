package com.jefisu.anlist.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.R
import com.jefisu.anlist.presentation.detail.util.Character
import com.jefisu.anlist.ui.theme.DavysGrey
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun CharacterInfo(
    character: Character,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(character.image),
            contentDescription = null,
            modifier = Modifier
                .height(70.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = character.name,
                style = defaultTextStyle,
                color = DavysGrey
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = character.voiceActor,
                style = defaultTextStyle,
                fontSize = 8.sp,
                color = PhilippineGray
            )
        }
    }
}

@Preview
@Composable
fun PreviewCharacterInfo() {
    CharacterInfo(
        character = Character(
            image = R.drawable.naruto,
            name = "Senkuu",
            voiceActor = "Kobayashi, Yuusuke"
        )
    )
}