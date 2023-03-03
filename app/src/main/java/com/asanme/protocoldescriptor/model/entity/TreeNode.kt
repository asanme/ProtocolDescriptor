package com.asanme.protocoldescriptor.model.entity

import com.google.gson.Gson
import java.util.*

data class TreeNode(
    val nodeID: UUID = UUID.randomUUID(),
    val name: String,
    val parentNode: TreeNode? = null,
) {
    var leftNode: TreeNode? = null
    var rightNode: TreeNode? = null

    fun toJson(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}