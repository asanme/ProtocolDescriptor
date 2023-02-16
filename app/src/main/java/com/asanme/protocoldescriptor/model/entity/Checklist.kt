package com.asanme.protocoldescriptor.model.entity

data class Checklist(
    val _id: String,
    val name: String,
    val tasks: List<ChecklistTask>
)