package com.asanme.protocoldescriptor

import com.asanme.protocoldescriptor.model.entity.ProtocolTask

//Testing file
object Main {
    @JvmStatic
    fun main(args: Array<String>) {

        val firstNode = ProtocolTask(
            "First Element",
            "First Description"
        )

        val secondNode = ProtocolTask(
            "Second Element",
            "Second Description"
        )
        val thirdNode = ProtocolTask(
            "Third Element",
            "Third Description"
        )

        firstNode.decisionYes = thirdNode
        firstNode.decisionNo = secondNode
        secondNode.decisionYes = thirdNode
        displayActionTree(firstNode)
    }

    private fun displayActionTree(node: ProtocolTask, level: Int = 0) {
        println("   ".repeat(level) + "→ Name: ${node.name}")
        println("   ".repeat(level) + "→ Description: ${node.description}")
        println("   ".repeat(level) + "→ ActionYes: ")
        node.decisionYes?.let { displayActionTree(it, level + 1) }
            ?: println("   ".repeat(level + 1) + "→ None")
        println("   ".repeat(level) + "→ ActionNo: ")
        node.decisionNo?.let { displayActionTree(it, level + 1) }
            ?: println("   ".repeat(level + 1) + "→ None")
    }
}