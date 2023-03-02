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
    private val api: RetrofitAPI
) : ViewModel() {
    private val _topicId = MutableStateFlow("")
    val topicId = _topicId.asStateFlow()

    private val _activities = MutableStateFlow<List<Checklist>>(emptyList())
    val activities = _activities.asStateFlow()

    private val _topic = MutableStateFlow(Topic("", ""))
    val topic = _topic.asStateFlow()

    private val _isDialogShown = MutableStateFlow(false)
    val isDialogShown = _isDialogShown.asStateFlow()

    suspend fun loadDatabase() = viewModelScope.launch {
        retrieveActivities()
        retrieveTitle()
    }

    suspend fun showDialog() = viewModelScope.launch {
        _isDialogShown.value = true
    }

    suspend fun hideDialog() = viewModelScope.launch {
        _isDialogShown.value = false
    }

    private suspend fun retrieveActivities() = viewModelScope.launch {
        try {
            api.getActivities(_topicId.value).body()?.let { retrievedActivities ->
                _activities.emit(retrievedActivities)
            }
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }

    private suspend fun retrieveTitle() = viewModelScope.launch {
        try {
            api.getTopic(_topicId.value).body()?.let { topic ->
                _topic.emit(topic)
            }
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }

    suspend fun deleteActivity(checklist: Checklist) = viewModelScope.launch {
        try {
            checklist._id?.let {
                if (api.deleteChecklist(it).isSuccessful) {
                    hideDialog()
                    retrieveActivities()
                } else {
                    // TODO Notify something went wrong
                }
            }
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }

    suspend fun updateTopicId(topicId: String) = viewModelScope.launch {
        _topicId.emit(topicId)
        loadDatabase()
    }
}