package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.model.entity.ActionEntity
import com.asanme.protocoldescriptor.model.enum.Decision
import com.asanme.protocoldescriptor.ui.component.*

@Composable
fun AddProtocolView() {
    Column(
        Modifier.fillMaxSize()
    ) {
        ProtocolHeader()
        ProtocolBody()
    }
}

@Composable
fun ProtocolHeader() {
    var protocolText by rememberSaveable {
        mutableStateOf("")
    }

    var acronymText by rememberSaveable {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(2f)) {
            CustomButton(
                onClick = {

                },
            ) {
                CustomImage(
                    imageVectorResource = R.drawable.arrow,
                    contentDescriptionResource = R.string.return_arrow
                )
            }
        }

        Box(
            Modifier.weight(5f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Add new protocol",
                fontFamily = interFamily,
                fontWeight = FontWeight.Normal,
                color = Color(3, 4, 94),
                fontSize = 20.sp,
            )
        }

        Box(Modifier.weight(2f))
    }

    CustomEditText(
        label = {
            Text(stringResource(id = R.string.protocol_label))
        },
        leadingIcon = {
            CustomIcon(
                R.drawable.protocol_icon,
                R.string.protocol_icon
            )
        },
        { newText ->
            protocolText = newText
        },
        protocolText,
        true
    )

    CustomEditText(
        label = {
            Text(stringResource(id = R.string.acronym_label))
        },
        leadingIcon = {
            CustomIcon(
                R.drawable.acronym_icon,
                R.string.acronym_icon
            )
        },
        { newText ->
            acronymText = newText
        },
        acronymText,
        true
    )
}

@Composable
private fun ProtocolBody() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TaskTitle()
        Divider(
            Modifier.padding(10.dp),
            thickness = 2.dp
        )
        ActionContainer()
    }
}

@Composable
private fun EditButton() {
    CustomButton(
        onClick = {},
        backgroundColor = Color(221, 224, 73)
    ) {
        CustomImage(
            imageVectorResource = R.drawable.pencil,
            contentDescriptionResource = R.string.pencil_icon,
            modifier = Modifier.size(25.dp),
        )
    }
}

@Composable
private fun TaskTitle() {
    Text(
        text = stringResource(id = R.string.task_label),
        fontFamily = interFamily,
        fontWeight = FontWeight.Bold,
        color = Color(3, 4, 94),
        fontSize = 24.sp
    )
}

@Composable
private fun ActionContainer() {
    val elements by rememberSaveable {
        mutableStateOf(
            ActionEntity(
                "First Element",
                "First Description"
            )
        )
    }

    val secondElement = ActionEntity("Second Element", "Second Description")
    elements.decisionYes = secondElement
    val thirdElement = ActionEntity("Third Element", "Third Description")
    secondElement.decisionYes = thirdElement
    val fourthElement = ActionEntity("Fourth Element", "Fourth Element")
    thirdElement.decisionNo = fourthElement

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        fun displayActionTree(node: ActionEntity, level: Int = 0) {
            item {
                ActionItem(
                    Modifier.padding(start = level.dp),
                    node
                )
            }

            item {
                DecisionItem(
                    Modifier.padding(start = level.dp),
                    Decision.YES
                )
            }

            node.decisionYes?.let {
                displayActionTree(it, level + 30)
            } ?: run {
                item {
                    NullItem(
                        Modifier.padding(start = level.dp),
                    )
                }
            }


            item {
                DecisionItem(
                    Modifier.padding(start = level.dp),
                    Decision.NO
                )
            }

            node.decisionNo?.let {
                displayActionTree(it, level + 30)
            } ?: run {
                item {
                    NullItem(
                        Modifier.padding(start = level.dp),
                    )
                }
            }
        }

        displayActionTree(elements)
    }
}

@Composable
fun NewElement(
    modifier: Modifier = Modifier,
) {
    var actionText by rememberSaveable {
        mutableStateOf("")
    }

    var descriptionText by rememberSaveable {
        mutableStateOf("")
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            backgroundColor = Color.White,
        ) {
            Column {
                CustomEditText(
                    label = {
                        Text(
                            stringResource(R.string.action_name),
                            color = Color(3, 4, 94),
                        )
                    },
                    onValueChange = { enteredAction ->
                        actionText = enteredAction
                    },
                    text = actionText
                )

                CustomEditText(
                    label = {
                        Text(
                            stringResource(R.string.action_description),
                            color = Color(3, 4, 94),
                        )
                    },
                    onValueChange = { enteredDescription ->
                        descriptionText = enteredDescription
                    },
                    text = descriptionText
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
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
fun TestPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        NewElement()
    }
}


@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
fun PreviewAddProtocol() {
    AddProtocolView()
}