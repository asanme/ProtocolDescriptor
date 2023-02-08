package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.ui.component.*
import com.asanme.protocoldescriptor.viewmodel.ProtocolViewModel

@Composable
fun ProtocolView(
    navController: NavHostController,
    topicViewModel: ProtocolViewModel,
    topicId: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        ProtocolHeader(topicId)
        ProtocolBody(navController, topicViewModel)
    }
}

@Composable
fun ProtocolHeader(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.weight(2f)) {
            MSquaredButton(
                onClick = {
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
            CustomTitle(title, fontSize = 24.sp)
        }

        Box(Modifier.weight(2f))
    }
}

@Composable
fun ProtocolBody(navController: NavHostController?, topicViewModel: ProtocolViewModel) {
    val topics = topicViewModel.listOfProtocols
    var searchString by rememberSaveable { mutableStateOf("") }

    CustomSearchBar(
        searchString,
        onValueChange = {
            searchString = it
        }
    )

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            topics.filter { topic ->
                topic.name.contains(searchString, true) || topic.acronym.contains(
                    searchString,
                    true
                )
            }
        ) { currentItem ->
            ProtocolDescriptionItem(currentItem)
        }
    }


}

@Preview(
    name = "ProtocolView",
    showSystemUi = true,
    device = Devices.NEXUS_6
)
@Composable
fun ProtocolViewPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        ProtocolHeader("Testing")
        ProtocolBody(null, ProtocolViewModel())
    }
}