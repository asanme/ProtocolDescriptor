package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asanme.protocoldescriptor.R

@Composable
fun AddNewElementButton() {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(40.dp))
            .background(Color(0xFF032F54))
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Box(modifier = Modifier.weight(5f))

        Text(
            "Add new topic",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(10f)
        )

        Row(
            modifier = Modifier.weight(5f),
            horizontalArrangement = Arrangement.End
        ) {
            MSquaredButton(
                backgroundColor = Color.White,
                shape = CircleShape,
                onClick = {

                },
            ) {
                MImageContainer(
                    imageVectorResource = R.drawable.add,
                    contentDescriptionResource = R.string.add_icon,
                )
            }
        }
    }
}

@Preview(
    name = "TopicsView",
)
@Composable
fun Testing() {
    AddNewElementButton()
}
