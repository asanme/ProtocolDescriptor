package com.asanme.protocoldescriptor.model.entity

data class Activity(
    val _id: String,
    val topicId: String,
    val name: String,
    val acronym: String,
    val type: String,
    val content: List<String>
)