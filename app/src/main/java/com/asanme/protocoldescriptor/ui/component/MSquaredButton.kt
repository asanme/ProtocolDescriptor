package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MSquaredButton(
    modifier: Modifier = Modifier,
    buttonSize: Dp = 45.dp,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit,
    content: @Composable (RowScope) -> Unit,
) {
    return Button(
        elevation = ButtonDefaults.elevation(5.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        modifier = modifier.size(buttonSize),
        content = content
    )
}
