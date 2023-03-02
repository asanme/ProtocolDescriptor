package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.model.enum.ViewRoutes
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.ui.component.custom.DeleteChecklistDialog
import com.asanme.protocoldescriptor.ui.theme.DarkBlue
import com.asanme.protocoldescriptor.viewmodel.ActivityViewModel
import kotlinx.coroutines.launch

@Composable
fun ActivityView(
    activityViewModel: ActivityViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        ProtocolHeader(activityViewModel)
        ProtocolBody(activityViewModel)
    }
}

@Composable
private fun ProtocolHeader(
    activityViewModel: ActivityViewModel
) {
    val title by activityViewModel.topic.collectAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.weight(2f)) {
            CustomSquaredButton(
                onClick = {
                    activityViewModel.goBack()
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
            CustomTitle(title.name, fontSize = 24.sp)
        }

        Box(Modifier.weight(2f))
    }
}

@Composable
private fun ProtocolBody(
    activityViewModel: ActivityViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val activities by activityViewModel.activities.collectAsState()
    var searchString by rememberSaveable { mutableStateOf("") }
    var clickedChecklist by remember { mutableStateOf(Checklist(null, "", "", mutableListOf())) }

    val isDialogShown by activityViewModel.isDialogShown.collectAsState()

    CustomSearchBar(
        searchString,
        onValueChange = {
            searchString = it
        }
    )

    Column(
        horizontalAlignment = Alignment.End
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(top = 5.dp, bottom = 60.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = activities.filter { protocol ->
                    protocol.name.contains(searchString, true)
                },
                key = { key ->
                    key._id.toString()
                }
            ) { currentItem ->
                ChecklistPreviewItem(
                    currentItem,
                    onItemClicked = {
                        activityViewModel.navigateToView("${ViewRoutes.ChecklistView.route}/${currentItem.topicId}/${currentItem._id}")
                    },
                    onItemLongClicked = {
                        coroutineScope.launch {
                            activityViewModel.showDialog()
                        }

                        clickedChecklist = currentItem
                    }
                )
            }
        }

        DeleteChecklistDialog(
            isMenuShown = isDialogShown,
            onDismiss = {
                coroutineScope.launch {
                    activityViewModel.hideDialog()
                }
            },
            onConfirm = {
                coroutineScope.launch {
                    activityViewModel.deleteActivity(clickedChecklist)
                }
            },
            clickedChecklist
        )

        FloatingActionButton(
            backgroundColor = Color.White,
            contentColor = DarkBlue,
            onClick = {
                activityViewModel.navigateToView("${ViewRoutes.AddChecklistView.route}/${activityViewModel.topic.value._id}")
            },
        ) {
            CustomIcon(
                imageVectorResource = R.drawable.add,
                contentDescriptionResource = R.string.add_icon
            )
        }
    }
}