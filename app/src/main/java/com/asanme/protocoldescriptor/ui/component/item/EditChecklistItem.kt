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
import com.asanme.protocoldescriptor.model.entity.ChecklistTask
import com.asanme.protocoldescriptor.model.enum.TaskStatus

@Composable
fun EditChecklistItem(
    modifier: Modifier = Modifier,
    task: ChecklistTask,
    onDiscardClicked: () -> Unit,
    onDoneClicked: (task: ChecklistTask) -> Unit,
    onEditClicked: (task: ChecklistTask) -> Unit,
) {
    when (task.status) {
        TaskStatus.Edit.status -> {
            EditTaskItem(task, modifier, onDiscardClicked, onDoneClicked)
        }

        TaskStatus.Pending.status -> {
            ViewTaskItem(modifier, task, onEditClicked)
        }
    }
}

@Composable
private fun EditTaskItem(
    task: ChecklistTask,
    modifier: Modifier,
    onDiscardClicked: () -> Unit,
    onDoneClicked: (task: ChecklistTask) -> Unit
) {
    var taskDescription by rememberSaveable {
        mutableStateOf(task.description)
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            CustomTextField(
                label = {
                    Text(
                        stringResource(R.string.task_description),
                    )
                },
                onValueChange = { enteredDescription ->
                    taskDescription = enteredDescription
                },
                text = taskDescription,
                trailingIcon = {
                    if (taskDescription.isEmpty()) {
                        CustomIcon(
                            imageVectorResource = R.drawable.error,
                            contentDescriptionResource = R.string.error_icon,
                            iconColor = Color.Red
                        )
                    }
                }
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
                    onClick = {
                        if (taskDescription.isNotEmpty()) {
                            onDoneClicked(
                                ChecklistTask(
                                    taskDescription,
                                    TaskStatus.Pending.status,
                                    task.taskID
                                )
                            )
                        } else {

                        }
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
private fun ViewTaskItem(
    modifier: Modifier,
    task: ChecklistTask,
    onEditClicked: (task: ChecklistTask) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.End
            ) {
                CustomIcon(
                    imageVectorResource = R.drawable.edit,
                    contentDescriptionResource = R.string.pencil_icon,
                    modifier = Modifier.clickable {
                        onEditClicked(
                            ChecklistTask(
                                task.description,
                                TaskStatus.Edit.status,
                                task.taskID
                            )
                        )
                    }
                )

                Text(
                    text = task.description,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color(3, 4, 94),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
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
private fun TestChecklistItem() {
    Column(Modifier.fillMaxSize()) {
        EditChecklistItem(
            task = ChecklistTask("", TaskStatus.Pending.status),
            onDiscardClicked = { },
            onDoneClicked = { },
            onEditClicked = { }
        )
    }
}