package com.asanme.protocoldescriptor.model.entity

import com.asanme.protocoldescriptor.model.enum.TaskStatus
import java.util.*

data class ChecklistTask(
    val description: String,
    val status: String = TaskStatus.Edit.status,
    val taskID: UUID = UUID.randomUUID(),
)