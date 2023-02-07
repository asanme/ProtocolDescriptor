package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FireExtinguisher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopicLazyItem(
    titleString: String,
    onItemClicked: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onItemClicked)
            .padding(vertical = 5.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FireExtinguisher,
                contentDescription = "Lorem",
                modifier = Modifier.size(115.dp)
            )

            Text(
                text = titleString,
                fontSize = 24.sp,
                color = Color(0xFF03045E),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(
    name = "Item",
    showSystemUi = true,
    device = Devices.NEXUS_6
)
@Composable
fun Preview() {
    TopicLazyItem(
        "Testing",
        onItemClicked = {

        }
    )
}