package com.asanme.protocoldescriptor.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProtocolTask(
    var name: String,
    var description: String,
    var decisionYes: ProtocolTask? = null,
    var decisionNo: ProtocolTask? = null
) : Parcelable