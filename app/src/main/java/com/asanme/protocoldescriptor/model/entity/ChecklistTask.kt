package com.asanme.protocoldescriptor.model.entity

import java.util.*

data class ChecklistTask(
    var name: String,
    var description: String,
    var status: String = "edit",
)
