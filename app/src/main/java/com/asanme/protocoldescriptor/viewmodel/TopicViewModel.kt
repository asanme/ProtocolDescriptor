package com.asanme.protocoldescriptor.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopicViewModel : ViewModel() {
    private val _listOfTopics = mutableStateListOf(
        "First element",
        "Second element",
        "Third element",
        "Fourth element",
        "Fifth element",
        "Sixth element",
    )

    val listOfTopics: MutableList<String> = _listOfTopics

    fun addNewItem(newValue: String) {
        _listOfTopics.add(newValue)
    }
}