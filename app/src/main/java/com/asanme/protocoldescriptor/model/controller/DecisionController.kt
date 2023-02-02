package com.asanme.protocoldescriptor.model.controller

import com.asanme.protocoldescriptor.model.entity.ProtocolTask

class DecisionController(
    private var actionTree: ProtocolTask
) {
    fun AddDecisions(
        actionYes: ProtocolTask?,
        actionNo: ProtocolTask?
    ): ProtocolTask {
        actionTree.decisionYes = actionYes
        actionTree.decisionNo = actionNo
        return actionTree
    }
}