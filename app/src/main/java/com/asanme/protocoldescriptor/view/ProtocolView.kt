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
import androidx.navigation.NavHostController
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.model.enum.Routes
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.ui.theme.DarkBlue
import com.asanme.protocoldescriptor.viewmodel.ActivityViewModel

@Composable
fun ProtocolView(
    navController: NavHostController,
    activityViewModel: ActivityViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        ProtocolHeader(navController, activityViewModel)
        ProtocolBody(navController, activityViewModel)
    }
}

@Composable
fun ProtocolHeader(
    navController: NavHostController,
    activityViewModel: ActivityViewModel
) {
    val title by activityViewModel.topic.collectAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.weight(2f)) {
            MSquaredButton(
                onClick = {
                    navController.navigateUp()
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
            CustomTitle(title.name, fontSize = 24.sp)
        }

        Box(Modifier.weight(2f))
    }
}

@Composable
fun ProtocolBody(
    navController: NavHostController,
    activityViewModel: ActivityViewModel
) {
    val activities by activityViewModel.activities.collectAsState()
    var searchString by rememberSaveable { mutableStateOf("") }

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
        ) {
            items(
                activities.filter { protocol ->
                    protocol.name.contains(searchString, true)
                }
            ) { currentItem ->
                ProtocolDescriptionItem(currentItem)
            }
        }

        FloatingActionButton(
            backgroundColor = Color.White,
            contentColor = DarkBlue,
            onClick = {
                navController.navigate("${Routes.AddChecklistView.route}/${activityViewModel.topic.value._id}")
            },
        ) {
            MIconContainer(
                imageVectorResource = R.drawable.add,
                contentDescriptionResource = R.string.add_icon
            )
        }
    }
}