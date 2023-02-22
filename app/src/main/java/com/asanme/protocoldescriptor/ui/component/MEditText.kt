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
    trailingIcon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
    text: String,
    singleLine: Boolean = false,
    backgroundColor: Color = Color.White,
    cornerShape: Dp = 25.dp,
    textColor: Color = Color(3, 4, 94),
    cursorColor: Color = Color.Black,

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
                backgroundColor = backgroundColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                trailingIconColor = Color(3, 4, 94),
                cursorColor = cursorColor,
                textColor = textColor,
            ),
            label = label,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            onValueChange = onValueChange,
            singleLine = singleLine,
        )
    }
}
