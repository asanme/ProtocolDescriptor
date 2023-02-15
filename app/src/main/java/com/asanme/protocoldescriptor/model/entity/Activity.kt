package com.asanme.protocoldescriptor.model.entity

import com.asanme.protocoldescriptor.model.enum.ActivityType

data class Activity(
    val _id: String,
    val topicId: String,
    val name: String,
    val acronym: String,
    val type: ActivityType,
    val content: String
)