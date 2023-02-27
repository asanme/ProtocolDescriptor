package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.model.entity.ProtocolTask

@Composable

fun EditProtocolItem() {
    var actionText by rememberSaveable {
        mutableStateOf("")
    }

    var descriptionText by rememberSaveable {
        mutableStateOf("")
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            CustomTextField(
                label = {
                    Text(
                        stringResource(R.string.action_name),
                    )
                },
                onValueChange = { enteredAction ->
                    actionText = enteredAction
                },
                text = actionText
            )

            CustomTextField(
                label = {
                    Text(
                        stringResource(R.string.task_description),
                    )
                },
                onValueChange = { enteredDescription ->
                    descriptionText = enteredDescription
                },
                text = descriptionText
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(224, 73, 106)
                    ),
                    onClick = {

                    },
                    modifier = Modifier
                        .weight(5f)
                        .height(40.dp),
                ) {
                    Text(
                        text = stringResource(R.string.discard),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(221, 224, 73)
                    ),
                    onClick = {

                    },
                    modifier = Modifier
                        .weight(5f)
                        .height(40.dp),
                ) {
                    Text(
                        text = stringResource(R.string.done),
                        color = Color(3, 4, 94),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun EditProtocolItemPreview(
    entity: ProtocolTask,
    modifier: Modifier = Modifier,
    onYesClicked: () -> Unit,
    onNoClicked: () -> Unit,
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entity.name,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(3, 4, 94),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.weight(8f)
                )

                CustomImage(
                    imageVectorResource = if (!isExpanded) R.drawable.expand else R.drawable.minimize,
                    contentDescriptionResource = if (!isExpanded) R.string.minimize_icon else R.string.expand_icon,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp)
                        .size(30.dp)
                        .clickable {
                            isExpanded = !isExpanded
                        }
                )
            }


            Text(
                text = entity.description,
                fontFamily = interFamily,
                fontWeight = FontWeight.Normal,
                color = Color(3, 4, 94),
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            )

            if (isExpanded) {
                Text(
                    text = stringResource(id = R.string.actions),
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(3, 4, 94),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify
                )

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .weight(5f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(221, 224, 73),
                        ),
                        shape = RoundedCornerShape(30.dp),
                        onClick = onYesClicked,
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(
                                text = "Yes:",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color(3, 4, 94),
                                fontSize = 13.sp,
                            )

                            Text(
                                text = "Not assigned",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(3, 4, 94),
                                fontSize = 13.sp,
                            )
                        }
                    }

                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .weight(5f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(224, 73, 106),
                        ),
                        shape = RoundedCornerShape(30.dp),
                        onClick = onNoClicked,
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(
                                text = "No:",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp,
                            )

                            Text(
                                text = "Assigned",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
private fun TestEditAction() {
    Column(Modifier.fillMaxSize()) {
        EditProtocolItem()
    }
}

@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
private fun TestPreview() {
    Column(Modifier.fillMaxSize()) {
        EditProtocolItemPreview(
            ProtocolTask(
                "Testing with the title",
                "Lorem ipsum dolor sit amet, "
            ),
            onYesClicked = {},
            onNoClicked = {}
        )
    }
}