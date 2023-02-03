package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MEditText(
    label: @Composable () -> Unit,
    leadingIcon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
    text: String,
    singleLine: Boolean = false,
    backgroundColor: Color = Color.White,
    cornerShape: Dp = 25.dp
) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(cornerShape),
    ) {
        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(cornerShape),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(3, 4, 94),
                backgroundColor = backgroundColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                trailingIconColor = Color(3, 4, 94),
            ),
            label = label,
            leadingIcon = leadingIcon,
            onValueChange = onValueChange,
            singleLine = singleLine
        )
    }
}
