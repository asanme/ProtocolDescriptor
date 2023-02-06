package com.asanme.protocoldescriptor.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.asanme.protocoldescriptor.ui.component.AddNewElementButton
import com.asanme.protocoldescriptor.ui.component.CustomSearchBar
import com.asanme.protocoldescriptor.ui.component.CustomTitle

@Composable
fun TopicsView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        TopicsHeader()
    }
}

@Composable
fun TopicsHeader() {
    var searchString by rememberSaveable {
        mutableStateOf("")
    }

    CustomTitle("Topics")

    CustomSearchBar(
        searchString,
        onValueChange = { newValue ->
            searchString = newValue
        }
    )

    AddNewElementButton()
}

@Composable
fun TopicsBody() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        item {

        }
    }
}

@Preview(
    name = "TopicsView",
    showSystemUi = true,
    device = Devices.NEXUS_6
)
@Composable
fun Testing() {
    AddNewElementButton()
}

@Preview(
    name = "TopicsView",
    showSystemUi = true,
    device = Devices.NEXUS_6
)
@Composable
fun TopicsPreview() {
    TopicsView()
}