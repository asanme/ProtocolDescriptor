package com.asanme.protocoldescriptor.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.asanme.protocoldescriptor.R

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
        contentScale = contentScale,
    )
}

@Preview
@Composable
private fun PreviewItem() {
    CustomImage(
        imageVectorResource = R.drawable.progress,
        contentDescriptionResource = R.string.task_description
    )
}