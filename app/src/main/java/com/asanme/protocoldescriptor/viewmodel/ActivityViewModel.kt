package com.asanme.protocoldescriptor.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.model.entity.Topic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.ConnectException

class ActivityViewModel(
    private val api: RetrofitAPI,
    private val navController: NavHostController
) : ViewModel() {
    private val _topicId = MutableStateFlow("")
    private val topicId = _topicId.asStateFlow()

    private val _activities = MutableStateFlow<List<Checklist>>(emptyList())
    val activities = _activities.asStateFlow()

    private val _topic = MutableStateFlow(Topic("", ""))
    val topic = _topic.asStateFlow()

    private val _isDialogShown = MutableStateFlow(false)
    val isDialogShown = _isDialogShown.asStateFlow()

    // Navigation Operations
    fun goBack() = viewModelScope.launch {
        navController.navigateUp()
    }

    fun navigateToView(route: String) = viewModelScope.launch {
        navController.navigate(route)
    }

    // Delete Menu Operations
    fun showDialog() = viewModelScope.launch {
        _isDialogShown.value = true
    }

    fun hideDialog() = viewModelScope.launch {
        _isDialogShown.value = false
    }

    // Modification Operations
    suspend fun updateTopicId(topicId: String) = viewModelScope.launch {
        _topicId.emit(topicId)
        loadDatabase()
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

    // Loading data operations
    private suspend fun loadDatabase() = viewModelScope.launch {
        retrieveActivities()
        retrieveTitle()
    }

    private suspend fun retrieveActivities() = viewModelScope.launch {
        try {
            api.getActivities(topicId.value).body()?.let { retrievedActivities ->
                _activities.emit(retrievedActivities)
            }
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }

    private suspend fun retrieveTitle() = viewModelScope.launch {
        try {
            api.getTopic(topicId.value).body()?.let { topic ->
                _topic.emit(topic)
            }
        } catch (err: ConnectException) {
            Log.i("ConnectionErr", "Failed to connect to server API")
        }
    }
}