package com.jefisu.anlist.presentation.search.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.R
import com.jefisu.anlist.ui.theme.PhilippineGray
import com.jefisu.anlist.ui.theme.defaultTextStyle

@Composable
fun SearchTextField(
    text: String,
    onTextChange: (String) -> Unit,
    textStyle: TextStyle = defaultTextStyle.copy(
        fontSize = 18.sp
    ),
    modifier: Modifier = Modifier,
    showAutomaticKeyboard: Boolean = true
) {
    val focusRequest = FocusRequester()
    LaunchedEffect(key1 = Unit) {
        if (showAutomaticKeyboard) {
            focusRequest.requestFocus()
        }
    }

    TextField(
        value = text,
        onValueChange = onTextChange,
        textStyle = textStyle,
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                fontSize = 18.sp
            )
        },
        trailingIcon = {
            if (text.isNotBlank()) {
                IconButton(onClick = { onTextChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = PhilippineGray,
            textColor = Color.White,
            cursorColor = Color.White,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .focusRequester(focusRequest)
            .then(modifier)
    )
}