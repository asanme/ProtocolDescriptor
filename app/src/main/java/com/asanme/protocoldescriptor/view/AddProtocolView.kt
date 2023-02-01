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
import androidx.compose.ui.text.style.TextAlign
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
        SingleElement(
            ActionEntity(
                "Testing with the title this is a little too long",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer rutrum ex lorem, non malesuada lectus dignissim sit amet. Praesent aliquet ante sit amet pellentesque tincidunt. Fusce interdum mi sem, ultricies imperdiet tellus auctor ut. Nunc volutpat mi tellus.",
            )
        )

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
fun SingleElement(
    entity: ActionEntity,
    modifier: Modifier = Modifier,
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Column {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            elevation = 5.dp,
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomImage(
                        imageVectorResource = R.drawable.minimize,
                        contentDescriptionResource = R.string.minimize_icon,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Text(
                    text = entity.name,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(3, 4, 94),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = entity.description,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(3, 4, 94),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify
                )

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
                        onClick = {

                        },
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(
                                text = "Yes:",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color(3, 4, 94),
                                fontSize = 14.sp,
                            )
                            Text(
                                text = "Not assigned",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color(3, 4, 94),
                                fontSize = 14.sp,
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
                        onClick = {

                        },
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
fun TestPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        SingleElement(
            ActionEntity(
                "Testing with the title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer rutrum ex lorem, non malesuada lectus dignissim sit amet. Praesent aliquet ante sit amet pellentesque tincidunt. Fusce interdum mi sem, ultricies imperdiet tellus auctor ut. Nunc volutpat mi tellus.",
            )
        )
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