package com.asanme.protocoldescriptor.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionEntity(
    var name: String,
    var description: String,
    var decisionYes: ActionEntity? = null,
    var decisionNo: ActionEntity? = null
) : Parcelable