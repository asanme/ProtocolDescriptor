package com.asanme.protocoldescriptor

import com.asanme.protocoldescriptor.model.entity.ActionEntity

object Main {
    @JvmStatic
    fun main(args: Array<String>) {

        val firstNode = ActionEntity(
            "First Element",
            "First Description"
        )

        val secondNode = ActionEntity(
            "Second Element",
            "Second Description"
        )
        val thirdNode = ActionEntity(
            "Third Element",
            "Third Description"
        )

        firstNode.decisionYes = thirdNode
        firstNode.decisionNo = secondNode
        secondNode.decisionYes = thirdNode
        displayActionTree(firstNode)
    }

    private fun displayActionTree(node: ActionEntity, level: Int = 0) {
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