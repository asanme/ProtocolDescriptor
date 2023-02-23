package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.enum.TaskStatus
import com.asanme.protocoldescriptor.model.helper.RetrofitHelper
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.viewmodel.ChecklistViewModel

@Composable
fun ChecklistView(
    navController: NavController?,
    checklistViewModel: ChecklistViewModel
) {
    checklistViewModel.retrieveTask()

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        TopControls(navController, checklistViewModel)
        CProgressBar()
        ChecklistBodyXD(checklistViewModel)
    }
}

@Composable
private fun TopControls(
    navController: NavController?,
    checklistViewModel: ChecklistViewModel
) {
    val currentChecklist = checklistViewModel.checklist.collectAsState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box {
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
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = currentChecklist.value.name,
                fontFamily = interFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF03045E),
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
private fun ChecklistBodyXD(
    checklistViewModel: ChecklistViewModel,
) {
    val currentChecklist = checklistViewModel.checklist.collectAsState()
    CustomTitle(text = stringResource(id = R.string.task_label))

    LazyColumn(
        contentPadding = PaddingValues(top = 5.dp, bottom = 60.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            currentChecklist.value.tasks,
            key = { task ->
                task.taskID
            }
        ) { currentTask ->
            ChecklistTaskItem(
                currentTask,
                onCheckedChange = { isChecked ->
                    var newStatus = if (isChecked) {
                        TaskStatus.Pending
                    } else {
                        TaskStatus.Done
                    }

                    checklistViewModel.modifyTask(
                        currentTask.taskID,
                        currentTask.copy(status = newStatus.status)
                    )
                }
            )
        }
    }
}

@Preview(showSystemUi = true, device = Devices.NEXUS_6)
@Composable
private fun PreviewChecklist() {
    ChecklistView(
        null,
        ChecklistViewModel(
            topicId = "",
            api = RetrofitHelper.getInstance().create(RetrofitAPI::class.java)
        ),
    )
}
