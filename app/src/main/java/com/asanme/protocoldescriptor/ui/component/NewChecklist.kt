package com.asanme.protocoldescriptor.ui.component

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
import com.asanme.protocoldescriptor.model.entity.ChecklistTask

@Composable

fun ChecklistItem(
    modifier: Modifier = Modifier,
    task: ChecklistTask,
    onDiscardClicked: () -> Unit,
    onDoneClicked: () -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
) {
    if (task.status == "edit") {
        var taskName by rememberSaveable {
            mutableStateOf(task.name)
        }

        var descriptionText by rememberSaveable {
            mutableStateOf(task.description)
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
                MEditText(
                    label = {
                        Text(
                            stringResource(R.string.task_name),
                        )
                    },
                    onValueChange = { enteredAction ->
                        taskName = enteredAction
                        onNameChange(enteredAction)
                    },
                    text = taskName
                )

                MEditText(
                    label = {
                        Text(
                            stringResource(R.string.action_description),
                        )
                    },
                    onValueChange = { enteredDescription ->
                        descriptionText = enteredDescription
                        onDescriptionChange(enteredDescription)
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
                        onClick = onDiscardClicked,
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
                        onClick = onDoneClicked,
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
    } else {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
            elevation = 5.dp,
            modifier = modifier,
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.name,
                        fontFamily = interFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(3, 4, 94),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.weight(8f)
                    )
                }

                Text(
                    text = task.description,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(3, 4, 94),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
fun TestChecklistItem() {
    Column(Modifier.fillMaxSize()) {
        ChecklistItem(
            task = ChecklistTask("", ""),
            onDiscardClicked = {

            },
            onDoneClicked = {

            },
            onNameChange = {

            },
            onDescriptionChange = {

            }
        )
    }
}