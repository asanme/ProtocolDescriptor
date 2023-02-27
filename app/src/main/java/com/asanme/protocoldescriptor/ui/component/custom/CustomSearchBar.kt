package com.asanme.protocoldescriptor.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asanme.protocoldescriptor.R

@Composable
fun CustomSearchBar(
    text: String,
    onValueChange: (String) -> Unit
) {
    CustomTextField(
        label = {
            Text(
                stringResource(R.string.search),
                color = Color.White
            )
        },
        leadingIcon = {
            CustomIcon(
                imageVectorResource = R.drawable.search,
                contentDescriptionResource = R.string.search_icon,
                iconColor = Color.White
            )
        },
        onValueChange = onValueChange,
        text = text,
        backgroundColor = Color(0xFF032F54),
        cornerShape = 15.dp,
        cursorColor = Color.White,
        textColor = Color.White
    )
}

@Preview
@Composable
private fun TopicsPreview() {
    CustomSearchBar(
        "Test",
        onValueChange = { }
    )
}