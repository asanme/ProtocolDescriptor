package com.asanme.protocoldescriptor.model.enum

sealed class Decision(val type: String) {
    object Yes : Decision("yes")
    object No : Decision("no")
    object None : Decision("none")
}