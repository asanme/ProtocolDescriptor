package com.asanme.protocoldescriptor.ui.component

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.ui.theme.DarkBlue
import com.asanme.protocoldescriptor.ui.theme.Purple200
import com.asanme.protocoldescriptor.ui.theme.Purple700
import com.asanme.protocoldescriptor.ui.theme.YellowG

@Composable
fun CustomProgressBar() {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        var tasksDone by remember { mutableStateOf(2) }
        var maxTasks by remember { mutableStateOf(4) }
        var expectedWidth by remember { mutableStateOf(0f) }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .onSizeChanged {
                    expectedWidth =
                        ((tasksDone.toFloat() / maxTasks.toFloat()) * it.width.toFloat())
                },
        ) {
            Row {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    CustomIcon(
                        imageVectorResource = R.drawable.progress,
                        contentDescriptionResource = R.string.progress
                    )

                    Text(
                        text = stringResource(id = R.string.progress),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = DarkBlue,
                        fontFamily = interFamily
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "$tasksDone / $maxTasks",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = DarkBlue,
                        fontFamily = interFamily
                    )
                }
            }

            Box(
                modifier = Modifier
                    .shadow(5.dp, shape = RoundedCornerShape(20.dp))
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .background(DarkBlue)
                        .height(30.dp)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .width(50.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White,
                                    YellowG
                                ),
                            )
                        )
                        .height(30.dp)
                )
            }
        }
    }
}

@Composable
fun ProgressBarWorkin() {
    val context = LocalContext.current
    var progressCount: Int by remember { mutableStateOf(0) }
    var progress by remember { mutableStateOf(0f) }

    /* to avoid the direct calculation of progress variable which is a Float
     and it can sometimes cause problems like it shows 0.4 to 0.400004 so, here I have use
     progressCount and we will increase and decrease it and then convert it to progress(Float)
     and then use that progress with our ProgressBar Width*/
    when (progressCount) {
        0 -> progress = 0.0f
        1 -> progress = 0.1f
        2 -> progress = 0.2f
        3 -> progress = 0.3f
        4 -> progress = 0.4f
        5 -> progress = 0.5f
        6 -> progress = 0.6f
        7 -> progress = 0.7f
        8 -> progress = 0.8f
        9 -> progress = 0.9f
        10 -> progress = 1.0f
    }

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 1000,
            easing = LinearOutSlowInEasing
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 30.dp, end = 30.dp)
    ) {
        // for the text above the progressBar
        Row(
            modifier = Modifier
                .widthIn(min = 30.dp)
                .fillMaxWidth(size),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "$progress")
        }
        // Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(17.dp)
        ) {
            // for the background of the ProgressBar
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(9.dp))
                    .background(Purple200)
            )
            // for the progress of the ProgressBar
            Box(
                modifier = Modifier
                    .fillMaxWidth(size)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(9.dp))
                    .background(Purple700)
                    .animateContentSize()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // decrease button
            OutlinedButton(onClick = {
                if (progressCount > 0) {
                    progressCount -= 2
                } else {
                    Toast.makeText(context, "You cannot decrease any more", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Text(text = "Decrease")
            }
            // increase Button
            Button(onClick = {
                if (progressCount < 10) {
                    progressCount += 2
                } else {
                    Toast.makeText(context, "You cannot increase more", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Increase")
            }
        }


    }

//    Use this when you want your progress bar should animate when you open your app
//    LaunchedEffect(key1 = true) {
//        progressCount = 7
//    }

}

@Preview
@Composable
private fun PreviewBar() {
    CustomProgressBar()
}