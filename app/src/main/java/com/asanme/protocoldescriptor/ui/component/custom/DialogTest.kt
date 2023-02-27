package com.asanme.protocoldescriptor.ui.component.custom

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogSample(
    isMenuShown: Boolean,
) {
    if (isMenuShown) {
        AlertDialog(
            onDismissRequest = {

            },
            title = {
                Text(text = "Dialog Title")
            },
            text = {
                Text("Here is a text ")
            },
            confirmButton = {
                Button(
                    onClick = {
                    }) {
                    Text("This is the Confirm Button")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                    }) {
                    Text("This is the dismiss Button")
                }
            }
        )
    }
}

@Preview
@Composable
private fun preview() {
    AlertDialogSample(true)
}