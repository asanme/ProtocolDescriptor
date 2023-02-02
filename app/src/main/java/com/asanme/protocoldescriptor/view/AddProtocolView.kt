package com.asanme.protocoldescriptor.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.model.entity.ProtocolTask
import com.asanme.protocoldescriptor.ui.component.*

@Composable
fun AddProtocolView() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ProtocolHeader()
        ProtocolBody()
    }
}

@Composable
fun ProtocolHeader() {
    TopControls()
    ProtocolFields()
}

@Composable
private fun ProtocolBody() {
    TaskTitle()
    Divider(thickness = 2.dp)
    ActionContainer()
}

@Composable
private fun TopControls() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.weight(2f)) {
            MSquaredButton(
                onClick = {

                },
            ) {
                MImageContainer(
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
                color = Color(0xFF03045E),
                fontSize = 20.sp,
            )
        }

        Box(Modifier.weight(2f))
    }
}

@Composable
private fun ProtocolFields() {
    var protocolText by rememberSaveable {
        mutableStateOf("")
    }

    var acronymText by rememberSaveable {
        mutableStateOf("")
    }

    MEditText(
        label = {
            Text(stringResource(id = R.string.protocol_label))
        },
        leadingIcon = {
            MIconContainer(
                R.drawable.protocol_icon,
                R.string.protocol_icon
            )
        },
        text = protocolText,
        singleLine = true,
        onValueChange = { newText ->
            protocolText = newText
        },
    )

    MEditText(
        label = {
            Text(stringResource(id = R.string.acronym_label))
        },
        leadingIcon = {
            MIconContainer(
                R.drawable.acronym_icon,
                R.string.acronym_icon
            )
        },
        text = acronymText,
        singleLine = true,
        onValueChange = { newText ->
            acronymText = newText
        },
    )
}

@Composable
private fun EditButton() {
    MSquaredButton(
        onClick = {},
        backgroundColor = Color(221, 224, 73)
    ) {
        MImageContainer(
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
    var listOfTest by rememberSaveable {
        mutableStateOf(mutableListOf<ProtocolTask>())
    }

    listOfTest.add(
        ProtocolTask(
            "Testing with the title",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer rutrum ex lorem, non malesuada lectus dignissim sit amet. Praesent aliquet ante sit amet pellentesque tincidunt. Fusce interdum mi sem, ultricies imperdiet tellus auctor ut. Nunc volutpat mi tellus.",
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        listOfTest.forEach { currentEntity ->
            item {
                LazyActionItem(
                    entity = currentEntity,
                    modifier = Modifier.padding(top = 1.dp),
                    onYesClicked = {
                        Log.i("Test", "Adding new element ${listOfTest.size}")
                        listOfTest.add(
                            currentEntity
                        )
                    },
                    onNoClicked = {

                    },
                )
            }

            item {
                LazyActionItemEdit()
            }
        }
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