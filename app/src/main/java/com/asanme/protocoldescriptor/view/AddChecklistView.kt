package com.asanme.protocoldescriptor.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
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
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.ui.theme.Pinkish
import com.asanme.protocoldescriptor.ui.theme.Yellowish
import com.asanme.protocoldescriptor.viewmodel.ActivityViewModel

@Composable
fun AddChecklistView(
    navController: NavController?,
    activityViewModel: ActivityViewModel
) {
    val topic by activityViewModel.topic.collectAsState()
    Log.i("ChecklistViewModel", topic._id)
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AddNewChecklistHeader(navController)
        ChecklistBody()
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
private fun ChecklistBody() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        TaskTitle()
        EditButton()
    }

    Divider(thickness = 2.dp)
    ActionContainer()
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
                    imageVectorResource = R.drawable.done,
                    contentDescriptionResource = R.string.done,
                    iconColor = Color.White
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
            contentDescriptionResource = R.string.return_arrow
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
private fun ActionContainer() {
    val currentTasks = ChecklistTask(
        "First Element",
        "Basic Description",
        "pending"
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ChecklistListElement(
                modifier = Modifier.padding(top = 5.dp),
                entity = currentTasks,
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