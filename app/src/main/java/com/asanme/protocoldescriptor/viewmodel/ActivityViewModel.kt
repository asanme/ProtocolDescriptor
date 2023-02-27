package com.asanme.protocoldescriptor.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.model.entity.Topic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException

class ActivityViewModel(
    private val topicId: String,
    private val api: RetrofitAPI
) : ViewModel() {
    init {
        viewModelScope.launch {
            retrieveActivities()
            retrieveTitle()
        }
    }

    private val _activities = MutableStateFlow<List<Checklist>>(emptyList())
    val activities = _activities.asStateFlow()

    private val _topic = MutableStateFlow(Topic("", ""))
    val topic = _topic.asStateFlow()

    private fun retrieveActivities() = viewModelScope.launch {
        try {
            api.getActivities(topicId).body()?.let { retrievedActivities ->
                _activities.emit(retrievedActivities)
            }
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }

    private fun retrieveTitle() = viewModelScope.launch {
        try {
            api.getTopic(topicId).body()?.let { topic ->
                _topic.emit(topic)
            }
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }
}