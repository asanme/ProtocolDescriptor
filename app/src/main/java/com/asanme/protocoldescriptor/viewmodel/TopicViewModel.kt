package com.asanme.protocoldescriptor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopicViewModel : ViewModel() {
    private val _listOfTopics = MutableLiveData(
        listOf(
            "First element",
            "Second element",
            "Third element",
            "Fourth element",
            "Fifth element",
            "Sixth element",
        )
    )

    val listOfTopics: LiveData<List<String>> = _listOfTopics
}