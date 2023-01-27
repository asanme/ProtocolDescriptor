package com.asanme.protocoldescriptor.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.fonts.interFamily

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
    leadingIcon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
    text: String,
    singleLine: Boolean = false
) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(25.dp),
    ) {
        TextField(
            value = text,
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
            singleLine = singleLine
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

@Composable
fun CustomCheckbox(
    text: String,
    onCheckedChange: () -> Unit
) {
    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(25.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 3.dp),
        ) {
            Text(
                text = "Final de trayecto",
                fontFamily = interFamily,
                fontWeight = FontWeight.Normal,
                color = Color(3, 4, 94),
                fontSize = 16.sp,
            )

            Switch(
                isChecked,
                onCheckedChange = {
                    isChecked = !isChecked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(3, 4, 94),
                    uncheckedThumbColor = Color(3, 4, 94),
                )
            )
        }
    }
}