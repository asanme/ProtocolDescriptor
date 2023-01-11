package com.asanme.protocoldescriptor.view.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.asanme.protocoldescriptor.misc.interFamily
import com.asanme.protocoldescriptor.view.component.button.*

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
            CustomRoundedButton(
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
    val scrollState = rememberScrollState()
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
            Text(
                text = stringResource(id = R.string.task_label),
                fontFamily = interFamily,
                fontWeight = FontWeight.Bold,
                color = Color(3, 4, 94),
                fontSize = 24.sp
            )

            CustomRoundedButton(
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

        Box(Modifier.padding(start = 10.dp)) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 0..25) {
                        item {
                            OptionItem(Modifier.padding(start = (i * 20).dp))
                        }
                        item {
                            TaskItem(Modifier.padding(start = (i * 10).dp))
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                ) {
                    CustomRoundedButton(
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
    }
}

@Composable
fun TaskItem(
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
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
            Text(text = "Description: ...")
        }
    }
}

@Composable
fun OptionItem(
    modifier: Modifier = Modifier
) {
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
            elevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
        ) {
            Row {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = "Name: ...",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(3, 4, 94)
                    )
                    Text(text = "Description: ...")
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewItem() {
    TaskItem()
}

@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
fun PreviewAddProtocol() {
    AddProtocolView()
}