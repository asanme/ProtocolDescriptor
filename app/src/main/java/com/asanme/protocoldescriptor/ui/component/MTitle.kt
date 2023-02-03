package com.asanme.protocoldescriptor.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.fonts.interFamily

@Composable
fun CustomTitle(
    text: String,
    fontSize: TextUnit = 36.sp,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        color = Color(0xFF032F54),
        fontFamily = interFamily
    )
}
