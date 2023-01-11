package com.asanme.protocoldescriptor.model

data class QuestionNode(
    var yesOutcome: QuestionNode? = null,
    var noOutcome: QuestionNode? = null,
)