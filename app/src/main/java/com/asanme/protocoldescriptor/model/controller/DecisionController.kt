package com.asanme.protocoldescriptor.model.controller

import com.asanme.protocoldescriptor.model.entity.ActionEntity

class DecisionController(
    private var actionTree: ActionEntity
) {
    fun AddDecisions(
        actionYes: ActionEntity?,
        actionNo: ActionEntity?
    ): ActionEntity {
        actionTree.decisionYes = actionYes
        actionTree.decisionNo = actionNo
        return actionTree
    }
}