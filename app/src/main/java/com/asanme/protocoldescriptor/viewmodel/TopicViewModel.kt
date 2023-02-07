package com.asanme.protocoldescriptor.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopicViewModel : ViewModel() {
    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    private val _listOfTopics = MutableLiveData(
        arrayListOf(
            "First element",
            "Second element",
            "Third element",
            "Fourth element",
            "Fifth element",
            "Sixth element",
        )
    )
    val listOfTopics: LiveData<ArrayList<String>> = _listOfTopics

    fun addNewItem(newValue: String) {
        _listOfTopics.value?.add(newValue)
        _listOfTopics.notifyObserver()
    }
}