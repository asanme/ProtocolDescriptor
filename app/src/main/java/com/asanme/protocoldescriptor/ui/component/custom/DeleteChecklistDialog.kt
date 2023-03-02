package com.asanme.protocoldescriptor.ui.component.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.ui.component.CustomTitle
import com.asanme.protocoldescriptor.ui.theme.DarkBlue

@Composable
fun DeleteChecklistDialog(
    isMenuShown: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    clickedChecklist: Checklist
) {
    if (isMenuShown) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTitle(stringResource(id = R.string.delete_checklist), fontSize = 25.sp)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, DarkBlue),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                        ),
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(5f)
                            .height(40.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            color = Color(3, 4, 94),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(224, 73, 106)
                        ),
                        onClick = onConfirm,
                        modifier = Modifier
                            .weight(5f)
                            .height(40.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.delete),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun preview() {
    DeleteChecklistDialog(
        isMenuShown = true,
        onDismiss = {

        },
        onConfirm = {

        },
        Checklist(null, "", "", mutableListOf())
    )
}