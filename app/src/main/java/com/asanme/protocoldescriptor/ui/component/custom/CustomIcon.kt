package com.asanme.protocoldescriptor.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.asanme.protocoldescriptor.R

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    @DrawableRes imageVectorResource: Int,
    @StringRes contentDescriptionResource: Int,
    iconColor: Color = Color(3, 4, 94),
) {
    Icon(
        ImageVector.vectorResource(id = imageVectorResource),
        contentDescription = stringResource(id = contentDescriptionResource),
        tint = iconColor,
        modifier = modifier
    )
}

@Preview
@Composable
private fun PreviewItem() {
    CustomIcon(
        imageVectorResource = R.drawable.progress,
        contentDescriptionResource = R.string.task_description
    )
}