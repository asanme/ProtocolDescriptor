package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.fonts.interFamily
import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.model.entity.ChecklistTask
import com.asanme.protocoldescriptor.model.enum.TaskStatus

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChecklistPreviewItem(
    item: Checklist,
    onItemClicked: () -> Unit,
    onItemLongClicked: () -> Unit,
) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onItemClicked,
                onLongClick = onItemLongClicked,
            )
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp),
        ) {
            Row {
                Text(
                    stringResource(R.string.name_label) + ": ",
                    color = Color(0xFF03045E),
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    item.name,
                    color = Color(0xFF03045E),
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
            }
            /*
            Row {
                Text(
                    stringResource(R.string.acronym_label) + ": ",
                    color = Color(0xFF03045E),
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                if (item.acronym.isNotEmpty()) {
                    Text(
                        item.acronym,
                        color = Color(0xFF03045E),
                        fontFamily = interFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                } else {
                    Text(
                        stringResource(R.string.no_acronym_label),
                        color = Color(0xFF03045E),
                        fontFamily = interFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }
             */
        }
    }
}

@Preview
@Composable
private fun PreviewItem() {
    ChecklistPreviewItem(
        Checklist(
            null,
            "",
            "",
            mutableListOf(ChecklistTask("Desc", TaskStatus.Pending.status))
        ),
        onItemClicked = {

        },
        onItemLongClicked = {

        }
    )
}