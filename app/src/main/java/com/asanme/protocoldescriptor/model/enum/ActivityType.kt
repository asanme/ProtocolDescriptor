package com.asanme.protocoldescriptor.model.enum

sealed class ActivityType(val type: String) {
    object Checklist : ActivityType("checklist")
    object BinaryTree : ActivityType("binary-tree")
}