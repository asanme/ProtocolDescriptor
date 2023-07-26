package com.asanme.protocoldescriptor.model.entity

import com.asanme.protocoldescriptor.model.enum.Position
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

    fun addNode(
        name: String = "",
        position: Position = Position.Right,
        targetNode: TreeNode
    ) {
        val newNode = TreeNode(
            name = name,
            parentNode = targetNode
        )

        when (position) {
            Position.Right -> targetNode.rightNode = newNode
            Position.Left -> targetNode.leftNode = newNode
        }

        applyChanges(targetNode)
    }

    private fun applyChanges(leafNode: TreeNode): TreeNode {
        var currentNode: TreeNode? = leafNode

        while (currentNode?.parentNode != null) {
            currentNode.parentNode?.let { parent ->
                if (currentNode == parent.leftNode) {
                    parent.leftNode = currentNode
                } else if (currentNode == parent.rightNode) {
                    parent.rightNode = currentNode
                }
            }
            currentNode = currentNode.parentNode
        }

        // Find the base root node
        var rootNode: TreeNode? = leafNode
        while (rootNode?.parentNode != null) {
            rootNode = rootNode.parentNode
        }

        // Displays the root node
        /*rootNode?.let {
            println("Binary Tree with Changes Applied starting from Root Node:")
            reverseTraverseTree(it)
        }*/

        // Displays the new binary tree
        printBinaryTree(rootNode!!)

        return rootNode
    }

    fun printBinaryTree(node: TreeNode?, prefix: String = "", isLeft: Boolean = true) {
        if (node == null) {
            return
        }

        val direction = if (isLeft) "└──" else "├──"
        val prefixWithDirection = prefix + direction

        println("$prefixWithDirection ${node.name} UUID: ${node.nodeID} ")

        printBinaryTree(node.leftNode, prefix + if (isLeft) "    " else "│   ", true)
        printBinaryTree(node.rightNode, prefix + if (isLeft) "│   " else "    ", false)
    }

    private fun reverseTraverseTree(node: TreeNode) {
        println(node.name)

        if (node.parentNode == null) {
            return
        } else {
            reverseTraverseTree(node.parentNode)
        }
    }
}