package com.asanme.protocoldescriptor.view.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    buttonSize: Dp = 50.dp,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit,
    content: @Composable (RowScope) -> Unit,
) {
    return Button(
        elevation = ButtonDefaults.elevation(50.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        modifier = modifier.size(buttonSize),
        content = content
    )
}

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    @DrawableRes imageVectorResource: Int,
    @StringRes contentDescriptionResource: Int,
) {
    Image(
        imageVector = ImageVector.vectorResource(id = imageVectorResource),
        contentDescription = stringResource(id = contentDescriptionResource),
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun CustomEditText(
    label: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    onValueChange: (String) -> Unit,
) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(25.dp),
    ) {
        TextField(
            value = "",
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            colors = textFieldColors(
                textColor = Color(3, 4, 94),
                backgroundColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color(3, 4, 94),
                cursorColor = Color.Black,
                trailingIconColor = Color(3, 4, 94),
            ),
            label = label,
            leadingIcon = leadingIcon,
            onValueChange = onValueChange,
        )
    }
}

@Composable
fun CustomIcon(
    @DrawableRes imageVectorResource: Int,
    @StringRes contentDescriptionResource: Int,
    iconColor: Color = Color(3, 4, 94),
) {
    Icon(
        ImageVector.vectorResource(id = imageVectorResource),
        contentDescription = stringResource(id = contentDescriptionResource),
        tint = iconColor,
        modifier = Modifier.padding(start = 10.dp)
    )
}