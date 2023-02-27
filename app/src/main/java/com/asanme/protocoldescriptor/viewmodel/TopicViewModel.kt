package com.asanme.protocoldescriptor.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.entity.Topic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException

class TopicViewModel(
    private val protocolApi: RetrofitAPI
) : ViewModel() {
    init {
        viewModelScope.launch {
            retrieveDatabase()
        }
    }

    private val _topics: MutableStateFlow<List<Topic>> = MutableStateFlow(emptyList())
    val topics = _topics.asStateFlow()

    private suspend fun retrieveDatabase() = viewModelScope.launch {
        try {
            protocolApi.getTopics().body()?.let { _topics.emit(it) }
            Log.i("TopicsValue", _topics.value.toString())
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }
}