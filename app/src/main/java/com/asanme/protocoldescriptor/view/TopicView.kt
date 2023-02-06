package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.asanme.protocoldescriptor.model.enum.Routes
import com.asanme.protocoldescriptor.ui.component.AddNewElementButton
import com.asanme.protocoldescriptor.ui.component.CustomSearchBar
import com.asanme.protocoldescriptor.ui.component.CustomTitle
import com.asanme.protocoldescriptor.ui.component.TopicLazyItem

@Composable
fun TopicsView(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        TopicsHeader()
        TopicsBody(navController)
    }
}

@Composable
fun TopicsHeader() {
    CustomTitle("Topics")
}

@Composable
fun TopicsBody(navController: NavHostController?) {
    var topics = remember { mutableStateListOf<String>() }
    topics.add("Item 1")
    topics.add("Item 2")
    topics.add("Item 3")
    topics.add("Item 4")
    topics.add("Item 5")
    topics.add("Item 6")
    topics.add("Item 7")
    topics.add("Item 8")
    topics.add("Item 9")
    topics.add("Item 10")
    topics.add("Item 11")
    topics.add("Item 12")
    topics.add("Item 13")
    topics.add("Item 14")
    topics.add("Item 15")
    topics.add("Item 16")
    topics.add("Item 17")
    topics.add("Item 18")
    topics.add("Item 19")
    topics.add("Item 20")
    topics.add("Item 21")
    topics.add("Item 22")
    topics.add("Item 23")
    topics.add("Item 24")
    topics.add("Item 25")

    var searchString by rememberSaveable {
        mutableStateOf("")
    }

    CustomSearchBar(
        searchString,
        onValueChange = { newValue ->
            searchString = newValue
        }
    )


    Column(
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val filteredList = topics.filter { input ->
                input.lowercase().contains(searchString.lowercase())
            }

            filteredList.forEach { currentItem ->
                item {
                    TopicLazyItem(
                        titleString = currentItem,
                        onItemClicked = {
                            navController?.navigate(Routes.AddProtocolView.route)
                        }
                    )
                }
            }
        }

        AddNewElementButton()
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
        TopicsBody(null)
    }
}