package com.asanme.protocoldescriptor.view

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.model.entity.ActionEntity
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
    val actions by rememberSaveable {
        mutableStateOf(
            ActionEntity(
                "Testing",
                "Description"
            )
        )
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
            AddActionButton()
        }


        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
            ActionContainer()
            AddActionButton()
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
    LazyColumn(
        modifier = Modifier
            .horizontalScroll(scrollState)
    ) {
        item {
            TaskItem(
                Modifier.padding(start = (0).dp)
            )
        }

        item {
            OptionItem(
                Modifier.padding(start = (0 + 10).dp)
            )
        }

        item {
            OptionItem(
                Modifier.padding(start = (0 + 10).dp),
                text = "No"
            )
        }
    }
}

@Composable
private fun AddActionButton() {
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
                onClick = {

                },
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
fun TaskItem(
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        modifier = modifier.padding(
            horizontal = 10.dp,
            vertical = 5.dp
        ),
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Name: ...",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(3, 4, 94)
            )

            Text(
                text = "Description: ...",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(3, 4, 94)
            )
        }
    }
}

@Composable
fun OptionItem(
    modifier: Modifier = Modifier,
    text: String = "Yes"
) {
    val context = LocalContext.current
    var wasClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
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
                .padding(vertical = 5.dp)
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
                        text = text,
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
fun AddActionMenu(isVisible: Boolean) {
    if (isVisible) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(224, 73, 106)),
        ) {
            Card(backgroundColor = Color.Transparent) {
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
            }

            Card(backgroundColor = Color.Transparent) {
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
            }

            CustomCheckbox(
                text = "Final de trayecto",
                onCheckedChange = {

                }
            )

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
        backgroundColor = Color(3, 47, 84),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
        ) {
            Text(
                text = "Final de trayecto",
                fontFamily = interFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp,
            )

            Switch(
                isChecked,
                onCheckedChange = {
                    isChecked = !isChecked
                },
                colors = SwitchDefaults.colors(
                    uncheckedTrackColor = Color.Yellow
                )
            )
        }
    }
}


@Preview
@Composable
fun PreviewItem() {
    AddActionMenu(true)
}

@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
fun PreviewAddProtocol() {
    AddProtocolView()
}