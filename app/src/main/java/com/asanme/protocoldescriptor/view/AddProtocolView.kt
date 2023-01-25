package com.asanme.protocoldescriptor.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.asanme.protocoldescriptor.view.component.CustomButton
import com.asanme.protocoldescriptor.view.component.CustomEditText
import com.asanme.protocoldescriptor.view.component.CustomIcon
import com.asanme.protocoldescriptor.view.component.CustomImage

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
    ) { currentText ->

    }

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
    ) { currentText ->

    }
}

@Composable
private fun ProtocolBody() {
    var isMenuShown by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TaskTitle()
            EditButton()
        }


        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxSize()
        ) {
            ActionContainer()
            AddActionButton {
                isMenuShown = true
            }

            AddActionMenu(
                isMenuShown,
                onAdd = {

                },
                onClose = {
                    isMenuShown = false
                }
            )
        }
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
    val scrollState = rememberScrollState()
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
        modifier = Modifier
            .horizontalScroll(scrollState)
            .fillMaxWidth()
    ) {
        fun displayActionTree(node: ActionEntity, level: Int = 0) {
            item {
                ActionItem(
                    Modifier.padding(
                        start = level.dp,
                    ),
                    node
                )
            }

            item {
                DecisionItem(
                    Modifier.padding(start = level.dp),
                    Decision.YES,
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
                    Decision.NO,
                )
            }

            node.decisionNo?.let {
                displayActionTree(it, evel + 30)
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

/* example of nested structure, but not functional
        item {
            TaskItem(
                Modifier.padding(start = (0).dp)
            )
        }

        item {
            OptionItem(
                Modifier.padding(start = (0 + 10).dp),
                Decision.YES,
                ActionEntity(
                    "test",
                    "no",
                )
            )
        }

        item {
            OptionItem(
                Modifier.padding(start = (0 + 10).dp),
                Decision.NO,
                ActionEntity(
                    "test",
                    "no",
                )
            )
        }
*/

@Composable
private fun AddActionButton(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            CustomButton(
                onClick = onClick,
                backgroundColor = Color(224, 73, 106)
            ) {
                CustomImage(
                    imageVectorResource = R.drawable.add,
                    contentDescriptionResource = R.string.add_icon,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}

@Composable
fun ActionItem(
    modifier: Modifier = Modifier,
    actionEntity: ActionEntity
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = actionEntity.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(3, 4, 94)
            )

            Text(
                text = actionEntity.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(3, 4, 94)
            )
        }
    }
}

@Composable
fun NullItem(
    modifier: Modifier = Modifier,
) {
    var wasClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            modifier = modifier
                .clickable {
                    wasClicked = true
                },
            backgroundColor = if (wasClicked) Color.Yellow else Color.White
        ) {
            Row {
                Column(
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = 20.dp
                    )
                ) {
                    Text(
                        text = Decision.NONE.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(3, 4, 94)
                    )
                }
            }
        }
    }
}

@Composable
fun DecisionItem(
    modifier: Modifier = Modifier,
    decision: Decision,
) {
    var wasClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(3, 4, 94))
                .size(20.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            modifier = Modifier
                .clickable {
                    wasClicked = true
                },
            backgroundColor = if (wasClicked) Color(224, 73, 106) else Color.White
        ) {
            Row {
                Column(
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = 20.dp
                    )
                ) {
                    Text(
                        text = decision.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (wasClicked) Color.White else Color(3, 4, 94)
                    )
                }
            }
        }
    }
}

@Composable
fun AddActionMenu(
    isVisible: Boolean,
    onAdd: () -> Unit,
    onClose: () -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                backgroundColor = Color(224, 73, 106),
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomStart = 40.dp,
                    bottomEnd = 40.dp,
                )
            ) {
                Column {
                    CustomCheckbox(
                        text = "Final de trayecto",
                        onCheckedChange = {

                        }
                    )

                    CustomEditText(
                        label = {
                            Text("Action name")
                        },
                        leadingIcon = {
                            CustomIcon(
                                imageVectorResource = R.drawable.protocol_icon,
                                contentDescriptionResource = R.string.protocol_icon
                            )
                        },
                        onValueChange = {

                        }
                    )

                    CustomEditText(
                        label = {
                            Text("Action description")
                        },
                        leadingIcon = {
                            CustomIcon(
                                imageVectorResource = R.drawable.acronym_icon,
                                contentDescriptionResource = R.string.acronym_icon
                            )
                        },
                        onValueChange = {

                        }
                    )


                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        CustomImage(
                            imageVectorResource = R.drawable.add,
                            contentDescriptionResource = R.string.add_icon,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    onAdd()
                                }
                        )

                        CustomImage(
                            imageVectorResource = R.drawable.cancel,
                            contentDescriptionResource = R.string.cancel_icon,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    onClose()
                                }
                        )
                    }
                }
            }
        }
    }
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

@Preview
@Composable
fun PreviewItem() {
    AddActionMenu(
        true,
        onAdd = {

        },
        onClose = {

        }
    )
}

@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
fun PreviewAddProtocol() {
    AddProtocolView()
}