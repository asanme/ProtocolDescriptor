package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSquaredButton(
    modifier: Modifier = Modifier,
    buttonSize: Dp = 50.dp,
    shape: Shape = RoundedCornerShape(10.dp),
    backgroundColor: Color = Color.White,
    onClick: () -> Unit,
    content: @Composable (RowScope) -> Unit,
) {
    return Button(
        elevation = ButtonDefaults.elevation(5.dp),
        shape = shape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        modifier = modifier.size(buttonSize),
        content = content
    )
}

@Preview
@Composable
private fun PreviewItem() {
    CustomSquaredButton(onClick = {}) {

    }
}