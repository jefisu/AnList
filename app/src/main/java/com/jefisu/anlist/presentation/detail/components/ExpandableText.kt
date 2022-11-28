package com.jefisu.anlist.presentation.detail.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun ExpandableText(
    longText: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 3,
    showMore: String = "… Show more",
    showLess: String = " Show less",
    clickColor: Color = Color.White,
    style: TextStyle = LocalTextStyle.current
) {
    var isExpanded by remember { mutableStateOf(false) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    var adjustedText by remember { mutableStateOf(longText) }
    val overflow = textLayoutResult?.hasVisualOverflow ?: false
    var showOverflow by remember { mutableStateOf(false) }

    LaunchedEffect(
        key1 = textLayoutResult
    ) {
        if (textLayoutResult == null) return@LaunchedEffect
        if (!isExpanded && overflow) {
            showOverflow = true
            val lastCharIndex = textLayoutResult!!.getLineEnd(minimizedMaxLines - 1)
            adjustedText = longText
                .substring(0, lastCharIndex)
                .dropLast(showMore.length)
                .dropLastWhile { it == ' ' || it == '.' }
        }
    }

    val annotatedText = buildAnnotatedString {
        if (isExpanded) {
            append(longText)
            withStyle(
                style = SpanStyle(color = clickColor, fontSize = style.fontSize)
            ) {
                pushStringAnnotation(
                    tag = "showLess",
                    annotation = "showLess"
                )
                append(showLess)
                addStyle(
                    style = SpanStyle(color = clickColor, fontSize = style.fontSize),
                    start = longText.length,
                    end = longText.length + showMore.length
                )
                pop()
            }
        } else {
            append(adjustedText)
            withStyle(
                style = SpanStyle(color = clickColor, fontSize = style.fontSize)
            ) {
                if (showOverflow) {
                    pushStringAnnotation(
                        tag = "showMore",
                        annotation = "showMore"
                    )
                    append(showMore)
                    addStyle(
                        style = SpanStyle(color = clickColor, fontSize = style.fontSize),
                        start = adjustedText.length,
                        end = adjustedText.length + showMore.length
                    )
                    pop()
                }
            }
        }
    }

    ClickableText(
        modifier = modifier,
        text = annotatedText,
        style = style,
        maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
        onTextLayout = { textLayoutResult = it },
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "showLess",
                start = offset,
                end = offset + showLess.length
            ).firstOrNull()?.let {
                isExpanded = !isExpanded
            }
            annotatedText.getStringAnnotations(
                tag = "showMore",
                start = offset,
                end = offset + showMore.length
            ).firstOrNull()?.let {
                isExpanded = !isExpanded
            }
        }
    )
}