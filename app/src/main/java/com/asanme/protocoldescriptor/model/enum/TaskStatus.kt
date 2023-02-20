package com.asanme.protocoldescriptor.model.enum

sealed class TaskStatus(val status: String) {
    object Done : TaskStatus("done")
    object Pending : TaskStatus("pending")
    object Edit : TaskStatus("edit")
}