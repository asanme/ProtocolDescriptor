package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
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
import androidx.navigation.NavController
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.model.entity.ChecklistTask
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.ui.theme.DarkBlue
import com.asanme.protocoldescriptor.ui.theme.Pinkish
import com.asanme.protocoldescriptor.ui.theme.Yellowish
import com.asanme.protocoldescriptor.viewmodel.ChecklistViewModel

@Composable
fun AddChecklistView(
    navController: NavController?,
    checklistViewModel: ChecklistViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        AddNewChecklistHeader(navController)
        ChecklistBody(checklistViewModel)
    }
}

@Composable
fun AddNewChecklistHeader(
    navController: NavController?
) {
    TopControls(navController)
    ChecklistFields()
}

@Composable
private fun ChecklistBody(checklistViewModel: ChecklistViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        TaskTitle()
        EditButton()
    }

    Divider(thickness = 2.dp)
    ActionContainer(checklistViewModel)
}

@Composable
private fun TopControls(
    navController: NavController?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.weight(2f)) {
            MSquaredButton(
                onClick = {
                    navController?.navigateUp()
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
                text = stringResource(R.string.add_checklist),
                fontFamily = interFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF03045E),
                fontSize = 20.sp,
            )
        }

        Box(
            Modifier.weight(2f),
            contentAlignment = Alignment.CenterEnd
        ) {
            MSquaredButton(
                backgroundColor = Pinkish,
                onClick = {
                },
            ) {
                MIconContainer(
                    imageVectorResource = R.drawable.add,
                    contentDescriptionResource = R.string.done,
                    iconColor = Color.White,
                    modifier = Modifier.size(90.dp)
                )
            }
        }
    }
}

@Composable
private fun ChecklistFields() {
    var protocolText by rememberSaveable {
        mutableStateOf("")
    }

    MEditText(
        label = {
            Text(stringResource(id = R.string.checklist_name))
        },
        leadingIcon = {
            MIconContainer(
                imageVectorResource = R.drawable.protocol_icon,
                contentDescriptionResource = R.string.protocol_icon
            )
        },
        text = protocolText,
        singleLine = true,
        onValueChange = { newText ->
            protocolText = newText
        },
    )
}

@Composable
private fun CreateChecklistButton() {
    MSquaredButton(
        onClick = {
        },
        backgroundColor = Pinkish
    ) {
        MImageContainer(
            imageVectorResource = R.drawable.add,
            contentDescriptionResource = R.string.add_icon
        )
    }
}

@Composable
private fun EditButton() {
    var isEditing by rememberSaveable {
        mutableStateOf(false)
    }

    MSquaredButton(
        onClick = {
            isEditing = !isEditing
        },
        backgroundColor = Yellowish
    ) {
        MImageContainer(
            imageVectorResource = if (!isEditing) R.drawable.pencil else R.drawable.done,
            contentDescriptionResource = R.string.return_arrow
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
private fun ActionContainer(
    checklistViewModel: ChecklistViewModel
) {
    val tasks = checklistViewModel.tasks

    Column(
        horizontalAlignment = Alignment.End
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = tasks,
                key = { key ->
                    key.taskID
                }
            ) { task ->
                ChecklistItem(
                    task = task,
                    onDiscardClicked = {
                        checklistViewModel.removeTask(task)
                    },
                    onDoneClicked = {
                        checklistViewModel.modifyStatus(task.taskID, "pending")
                    },
                    onNameChange = { newName ->
                        checklistViewModel.modifyName(task.taskID, newName)
                    },
                    onDescriptionChange = { newDescription ->
                        checklistViewModel.modifyDescription(task.taskID, newDescription)
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = {
                checklistViewModel.addNewTask(
                    ChecklistTask(
                        "",
                        "",
                    )
                )
            },
            backgroundColor = Color.White
        ) {
            MIconContainer(
                imageVectorResource = R.drawable.add,
                contentDescriptionResource = R.string.add_icon,
                iconColor = DarkBlue
            )
        }
    }
}

@Preview(
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
fun PreviewAddChecklist() {

}