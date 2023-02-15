package com.asanme.protocoldescriptor.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.entity.Topic
import com.asanme.protocoldescriptor.model.helper.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopicViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            retrieveDatabase()
        }
    }

    private val _listOfTopics = mutableStateListOf(
        "First element",
        "Second element",
        "Third element",
        "Fourth element",
        "Fifth element",
        "Sixth element",
    )

    val listOfTopics: MutableList<String> = _listOfTopics

    private val _topics: MutableStateFlow<List<Topic>> = MutableStateFlow(emptyList())
    val topics = _topics.asStateFlow()

    /*
    fun addNewItem(newValue: String) {
        _listOfTopics.add(newValue)
    }
    */

    private suspend fun retrieveDatabase() {
        val protocolApi = RetrofitHelper.getInstance().create(RetrofitAPI::class.java)
        viewModelScope.launch {
            protocolApi.getTopics().body()?.let { _topics.emit(it) }
            Log.i("TopicsValue", _topics.value.toString())
        }
    }
}