package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopicLazyItem() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
    ) {
        Text(
            text = "Lorem",
            fontSize = 24.sp,
            color = Color(0xFF03045E)
        )
    }
}

@Preview(
    name = "Item",
    showSystemUi = true,
    device = Devices.NEXUS_6
)
@Composable
fun Preview() {
    TopicLazyItem()
}