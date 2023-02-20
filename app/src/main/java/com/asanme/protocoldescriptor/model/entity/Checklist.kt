package com.asanme.protocoldescriptor.model.entity

data class Checklist(
    val _id: String? = null,
    val topicId: String,
    val name: String,
    val tasks: MutableList<ChecklistTask>
)