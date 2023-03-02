package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.asanme.protocoldescriptor.model.enum.TaskStatus
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.ui.theme.DarkBlue
import com.asanme.protocoldescriptor.ui.theme.Pinkish
import com.asanme.protocoldescriptor.viewmodel.ChecklistViewModel
import kotlinx.coroutines.launch

@Composable
fun AddChecklistView(
    navController: NavController?,
    checklistViewModel: ChecklistViewModel
) {
    val shouldReturn by checklistViewModel.shouldReturn.collectAsState()

    if(shouldReturn) {
        navController?.navigateUp()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        AddNewChecklistHeader(navController, checklistViewModel)
        ChecklistBody(checklistViewModel)
    }
}

@Composable
fun AddNewChecklistHeader(
    navController: NavController?,
    checklistViewModel: ChecklistViewModel
) {
    TopControls(
        navController,
        checklistViewModel
    )

    ChecklistFields(checklistViewModel)
}

@Composable
private fun ChecklistBody(
    checklistViewModel: ChecklistViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        TaskTitle()
    }

    TaskContainer(checklistViewModel)
}

@Composable
private fun TopControls(
    navController: NavController?,
    checklistViewModel: ChecklistViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.weight(2f)) {
            CustomSquaredButton(
                onClick = {
                    navController?.navigateUp()
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
            CustomSquaredButton(
                backgroundColor = Pinkish,
                onClick = {
                    checklistViewModel.publishChecklist()
                },
            ) {
                CustomIcon(
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
private fun ChecklistFields(checklistViewModel: ChecklistViewModel) {
    var checklistName by rememberSaveable {
        mutableStateOf("")
    }

    CustomTextField(
        label = {
            Text(stringResource(id = R.string.checklist_name))
        },
        leadingIcon = {
            CustomIcon(
                imageVectorResource = R.drawable.protocol_icon,
                contentDescriptionResource = R.string.protocol_icon
            )
        },
        text = checklistName,
        singleLine = true,
        onValueChange = { newText ->
            checklistName = newText
            checklistViewModel.changeTitle(newText)
        },
        trailingIcon = {
            if (checklistName.isEmpty()) {
                CustomIcon(
                    imageVectorResource = R.drawable.error,
                    contentDescriptionResource = R.string.error_icon,
                    iconColor = Color.Red
                )
            }
        }
    )
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
private fun TaskContainer(
    checklistViewModel: ChecklistViewModel
) {
    val tasks = checklistViewModel.tasks

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(top = 5.dp, bottom = 60.dp)
        ) {
            items(
                items = tasks,
                key = { key ->
                    key.taskID
                }
            ) { task ->
                EditChecklistItem(
                    task = task,
                    onDiscardClicked = {
                        checklistViewModel.removeTask(task)
                    },
                    onDoneClicked = { newTask ->
                        checklistViewModel.modifyTask(task.taskID, newTask)
                    },
                    onEditClicked = { taskToModify ->
                        checklistViewModel.modifyTask(task.taskID, taskToModify)
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = {
                checklistViewModel.addNewTask(
                    ChecklistTask(
                        "",
                        TaskStatus.Edit.status,
                    )
                )
            },
            backgroundColor = Color.White
        ) {
            CustomIcon(
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