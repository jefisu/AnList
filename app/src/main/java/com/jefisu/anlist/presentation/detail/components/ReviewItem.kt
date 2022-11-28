package com.jefisu.anlist.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.R
import com.jefisu.anlist.presentation.detail.util.Review
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.GraniteGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun ReviewItem(
    review: Review,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(review.profileImage),
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
                    text = review.username,
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
                longText = review.text,
                minimizedMaxLines = 3,
                style = defaultTextStyle.copy(
                    fontSize = 10.sp,
                    color = GraniteGray,
                    lineHeight = 14.sp,
                ),
                clickColor = DarkSlateBlue
            )
        }
    }
}

@Preview
@Composable
fun PreviewReviewItem() {
    ReviewItem(
        review = Review(
            profileImage = R.drawable.jefisu_profile,
            username = "jefisu",
            text = LoremIpsum(30).values.first(),
            date = "11/21/2022",
        ),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}