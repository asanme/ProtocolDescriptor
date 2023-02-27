package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asanme.protocoldescriptor.model.entity.ChecklistTask
import com.asanme.protocoldescriptor.model.enum.TaskStatus

@Composable
fun ViewChecklistItem(
    task: ChecklistTask,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.status == TaskStatus.Done.status,
                onCheckedChange = onCheckedChange
            )

            Text(
                text = task.description,
                style = if (task.status == TaskStatus.Done.status) {
                    TextStyle(
                        textDecoration =
                        TextDecoration.LineThrough
                    )
                } else {
                    TextStyle(
                        textDecoration =
                        TextDecoration.None
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewItem() {
    ViewChecklistItem(
        ChecklistTask("kajsfñlasjdfñjs afkasjfklasjf añfjasdfj askjflkasjfklsj afdkljsfkljsakfdljsakld fjsdkajf  kslajdfksla"),
        onCheckedChange = {}
    )
}