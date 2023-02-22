package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.helper.RetrofitHelper
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
        AddNewChecklistHeader(navController, checklistViewModel)
    }
}

@Preview(
    showSystemUi = true,
    device = Devices.NEXUS_6
)
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
