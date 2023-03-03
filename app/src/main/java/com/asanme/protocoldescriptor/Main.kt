package com.asanme.protocoldescriptor

import com.asanme.protocoldescriptor.model.entity.TreeNode
import com.asanme.protocoldescriptor.model.enum.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type

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

        //showTree(rootNode)
        addNode(name = "Added node", position = Position.Right, targetNode = rightNode3)
    }

    private fun reverseTraverseTree(node: TreeNode) {
        println(node.name)

        if (node.parentNode == null) {
            return
        } else {
            reverseTraverseTree(node.parentNode)
        }
    }

    private fun addNode(
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

        rootNode?.let {
            println("Binary Tree with Changes Applied starting from Root Node:")
            reverseTraverseTree(it)
        }

        printBinaryTree(rootNode!!)

        println("JSON FORMAT: --------")

        val gson = Gson()
        val json = gson.toJson(rootNode)
        val file = File("tree.json")
        file.writeText(json)

        // Deserialize the tree from the JSON file
        val type: Type = object : TypeToken<TreeNode>() {}.type
        val jsonTree = file.readText()
        val deserializedRootNode = gson.fromJson<TreeNode>(jsonTree, type)
        printTree(deserializedRootNode)
        println("END OF JSON FORMAT: --------")

        return rootNode ?: throw IllegalStateException("Root node not found")
    }

    private fun printBinaryTree(node: TreeNode?, prefix: String = "", isLeft: Boolean = true) {
        if (node == null) {
            return
        }

        val direction = if (isLeft) "└──" else "├──"
        val prefixWithDirection = prefix + direction

        println("$prefixWithDirection ${node.name} UUID: ${node.nodeID} ")

        printBinaryTree(node.leftNode, prefix + if (isLeft) "    " else "│   ", true)
        printBinaryTree(node.rightNode, prefix + if (isLeft) "│   " else "    ", false)
    }

    fun printTree(node: TreeNode?, prefix: String = "", isLeft: Boolean = true) {
        if (node == null) {
            return
        }

        println("${prefix}${if (isLeft) "└── " else "├── "}${node.name}")

        printTree(node.leftNode, "$prefix${if (isLeft) "    " else "│   "}", true)
        printTree(node.rightNode, "$prefix${if (isLeft) "    " else "│   "}", false)
    }
}