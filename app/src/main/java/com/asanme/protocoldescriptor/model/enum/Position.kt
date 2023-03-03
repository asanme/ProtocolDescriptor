package com.asanme.protocoldescriptor.model.enum

sealed class Position(val type: String) {
    object Left : Position("left")
    object Right : Position("right")
}