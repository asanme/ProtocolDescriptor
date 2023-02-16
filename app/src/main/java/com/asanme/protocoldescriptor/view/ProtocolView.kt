package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.viewmodel.ProtocolViewModel

@Composable
fun ProtocolView(
    navController: NavHostController,
    protocolViewModel: ProtocolViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        ProtocolHeader(navController, protocolViewModel)
        ProtocolBody(navController, protocolViewModel)
    }
}

@Composable
fun ProtocolHeader(
    navController: NavHostController,
    protocolViewModel: ProtocolViewModel
) {
    val title by protocolViewModel.topic.collectAsState()

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
    protocolViewModel: ProtocolViewModel
) {
    val activities by protocolViewModel.activities.collectAsState()
    var searchString by rememberSaveable { mutableStateOf("") }

    CustomSearchBar(
        searchString,
        onValueChange = {
            searchString = it
        }
    )

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(
            activities.filter { protocol ->
                protocol.name.contains(searchString, true) || protocol.acronym.contains(
                    searchString,
                    true
                )
            }
        ) { currentItem ->
            ProtocolDescriptionItem(currentItem)
        }
    }
}