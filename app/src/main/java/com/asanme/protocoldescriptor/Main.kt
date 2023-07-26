package com.asanme.protocoldescriptor

import com.asanme.protocoldescriptor.model.entity.TreeNode
import com.asanme.protocoldescriptor.model.enum.Position

//Testing file
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        var rootNode = TreeNode(
            name = "Root Node",
        )

        var leftNode = TreeNode(
            name = "Left Node",
            parentNode = rootNode,
        )

        rootNode.leftNode = leftNode

        var rightNode = TreeNode(
            name = "Right Node",
            parentNode = rootNode,
        )

        rootNode.rightNode = rightNode

        var leftNode2 = TreeNode(
            name = "Left Node 2",
            parentNode = leftNode,
        )

        leftNode.leftNode = leftNode2

        var rightNode2 = TreeNode(
            name = "Right Node 2",
            parentNode = leftNode,
        )

        leftNode.rightNode = rightNode2

        var rightNode3 = TreeNode(
            name = "Right Node 3",
            parentNode = rightNode2,
        )

        rightNode2.rightNode = rightNode3

        rootNode.addNode(
            name = "Added node",
            position = Position.Right,
            targetNode = rightNode3
        )
    }
}