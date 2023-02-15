package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.asanme.protocoldescriptor.model.enum.Routes
import com.asanme.protocoldescriptor.ui.component.CustomSearchBar
import com.asanme.protocoldescriptor.ui.component.CustomTitle
import com.asanme.protocoldescriptor.ui.component.NewTopicMenu
import com.asanme.protocoldescriptor.ui.component.TopicLazyItem
import com.asanme.protocoldescriptor.viewmodel.TopicViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

@Composable
fun TopicsView(navController: NavHostController, topicViewModel: TopicViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        TopicsHeader()
        TopicsBody(navController, topicViewModel)
    }
}

@Composable
fun TopicsHeader() {
    CustomTitle("Topics")
}

@Composable
fun TopicsBody(navController: NavHostController?, topicViewModel: TopicViewModel) {
    val topics = topicViewModel.listOfTopics
    var searchString by rememberSaveable { mutableStateOf("") }

    CustomSearchBar(
        searchString,
        onValueChange = {
            searchString = it
        }
    )

    Column {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f),
        ) {
            items(
                topics.filter { topic ->
                    topic.contains(searchString, true)
                }
            ) { currentItem ->
                TopicLazyItem(
                    currentItem,
                    onItemClicked = {
                        navController?.navigate("${Routes.ProtocolView.route}/$currentItem")
                    }
                )
            }
        }

        NewTopicMenu(
            onTopicAdd = { }
        )
    }
}

@Preview(
    name = "TopicsView",
    showSystemUi = true,
    device = Devices.NEXUS_6
)
@Composable
fun TopicsPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        TopicsHeader()
        TopicsBody(null, TopicViewModel())
    }
}