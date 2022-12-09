package com.jefisu.anlist.presentation.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.GraniteGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun ReviewItem(
    review: Review,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = review.user.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = review.user.username,
                    style = defaultTextStyle,
                    color = Color.Black
                )
                Text(
                    text = review.date,
                    style = defaultTextStyle,
                    fontSize = 8.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            ExpandableText(
                longText = review.review,
                minimizedMaxLines = 3,
                style = defaultTextStyle.copy(
                    fontSize = 10.sp,
                    color = GraniteGray,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Justify
                ),
                clickColor = DarkSlateBlue
            )
        }
    }
}