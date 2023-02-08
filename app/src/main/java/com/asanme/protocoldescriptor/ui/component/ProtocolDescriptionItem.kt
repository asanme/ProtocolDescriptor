package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.model.entity.ProtocolDescription

@Composable
fun ProtocolDescriptionItem(item: ProtocolDescription) {
    Card(
        elevation = 5.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                item.name,
                color = Color(0xFF03045E),
                fontFamily = interFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            if (item.acronym.isNotEmpty()) {
                Text(
                    item.acronym,
                    color = Color(0xFF03045E),
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewProtocolDescriptionItem() {
    ProtocolDescriptionItem(ProtocolDescription("Testing"))
}
