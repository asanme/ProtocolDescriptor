package com.asanme.protocoldescriptor.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.asanme.protocoldescriptor.model.entity.ProtocolDescription

class ProtocolViewModel : ViewModel() {
    private val _listOfProtocols = mutableStateListOf(
        ProtocolDescription("START"),
        ProtocolDescription("END"),
        ProtocolDescription("THUMA"),
        ProtocolDescription("TESTING"),
        ProtocolDescription("NEW KEY"),
    )

    val listOfProtocols: MutableList<ProtocolDescription> = _listOfProtocols
}