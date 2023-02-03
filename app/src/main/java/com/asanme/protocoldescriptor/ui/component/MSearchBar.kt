package com.asanme.protocoldescriptor.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R

@Composable
fun CustomSearchBar() {
    MEditText(
        label = {
            Text(
                "Search",
                color = Color.White
            )
        },
        leadingIcon = {
            MIconContainer(
                imageVectorResource = R.drawable.search,
                contentDescriptionResource = R.string.search_icon,
                iconColor = Color.White
            )
        },
        onValueChange = {

        },
        text = "",
        backgroundColor = Color(0xFF032F54),
        cornerShape = 15.dp
    )
}

@Preview(
    name = "TopicsView",
    showSystemUi = true,
    device = Devices.NEXUS_6
)
@Composable
fun TopicsPreview() {
    CustomSearchBar()
}
