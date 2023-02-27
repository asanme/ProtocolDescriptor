package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily

@Composable
fun CustomTopicButton(
    onTopicAdd: (String) -> Unit
) {
    var isMenuShown by rememberSaveable { mutableStateOf(false) }
    var topicText by rememberSaveable { mutableStateOf("") }

    if (!isMenuShown) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .background(Color(0xFF032F54))
                .fillMaxWidth()
                .padding(4.dp),
        ) {
            Box(modifier = Modifier.weight(5f))

            Text(
                "Add new topic",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(10f)
            )

            Row(
                modifier = Modifier.weight(5f),
                horizontalArrangement = Arrangement.End
            ) {
                CustomSquaredButton(
                    backgroundColor = Color.White,
                    shape = CircleShape,
                    onClick = {
                        isMenuShown = !isMenuShown
                    },
                ) {
                    CustomImage(
                        imageVectorResource = R.drawable.add,
                        contentDescriptionResource = R.string.add_icon,
                    )
                }
            }
        }
    } else {
        Card(
            backgroundColor = Color(0xFF032F54),
            elevation = 5.dp,
            shape = RoundedCornerShape(21.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    "Add Topic",
                    color = Color.White,
                    fontSize = 20.sp
                )

                CustomTextField(
                    label = {
                        Text(
                            text = stringResource(R.string.topic),
                            color = Color(0xFF032F54)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.message),
                            contentDescription = stringResource(R.string.message_icon),
                            tint = Color(0xFF032F54),
                        )
                    },
                    onValueChange = { newValue ->
                        topicText = newValue
                    },
                    text = topicText,
                )

                CustomTextField(
                    label = {
                        Text(
                            text = stringResource(R.string.icon),
                            color = Color(0xFF032F54)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.brush),
                            contentDescription = stringResource(R.string.brush_icon),
                            tint = Color(0xFF032F54),
                        )
                    },
                    onValueChange = {

                    },
                    text = "",
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { onTopicAdd(topicText) },
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults
                            .buttonColors(backgroundColor = Color(0xFFDDE049)),
                        modifier = Modifier
                            .weight(8f)
                            .height(50.dp),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Normal,
                                text = stringResource(R.string.new_topic),
                                color = Color(0xFF032F54),
                                textAlign = TextAlign.Center
                            )

                            Icon(
                                painter = painterResource(R.drawable.topic),
                                contentDescription = stringResource(R.string.topic_icon),
                                tint = Color(0xFF032F54)
                            )
                        }
                    }

                    Button(
                        shape = CircleShape,
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White
                        ),
                        onClick = {
                            isMenuShown = !isMenuShown
                        },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.close),
                            contentDescription = stringResource(R.string.close_icon),
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "TopicsView",
)
@Composable
fun Testing() {
    CustomTopicButton(onTopicAdd = {})
}