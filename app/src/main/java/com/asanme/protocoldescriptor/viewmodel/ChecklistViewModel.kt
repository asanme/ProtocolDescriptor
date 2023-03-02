package com.asanme.protocoldescriptor.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.entity.Checklist
import com.asanme.protocoldescriptor.model.entity.ChecklistTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class ChecklistViewModel(
    private val api: RetrofitAPI
) : ViewModel() {
    private var _checklistId = MutableStateFlow<String?>(null)
    val checklistId = _checklistId.asStateFlow()

    private var _topicId = MutableStateFlow("")
    private val topicId = _topicId.asStateFlow()

    private var _tasks = mutableStateListOf<ChecklistTask>()
    val tasks: MutableList<ChecklistTask> = _tasks

    private var _checklist = MutableStateFlow(
        Checklist(
            topicId = topicId.value,
            name = "", tasks = tasks
        )
    )
    val checklist = _checklist.asStateFlow()

    private var _shouldReturn = MutableStateFlow(false)
    val shouldReturn = _shouldReturn.asStateFlow()


    fun updateChecklistId(checklistId: String) = viewModelScope.launch {
        _checklistId.emit(checklistId)
    }

    fun updateTopicId(topicId: String) = viewModelScope.launch {
        _topicId.emit(topicId)
    }

    fun uploadChanges() = viewModelScope.launch {
        try {
            _checklist.value = _checklist.value.copy(tasks = tasks)
            checklistId.value?.let { checklistId ->
                val response = api.putChecklist(
                    topicId.value,
                    checklistId,
                    checklist.value
                )

                if (response.isSuccessful) {
                    response.body()?.let { retrievedChecklist ->
                        Log.e("Success", "Uploaded successfully")
                    }
                } else {
                    Log.e("Error", "Error getting response from API")
                }
            }
        } catch (err: Exception) {
            Log.e("Error", err.stackTraceToString())
        }
    }

    fun retrieveTask() = viewModelScope.launch {
        try {
            checklistId.value?.let { checklistId ->
                val response = api.getChecklist(topicId.value, checklistId)
                if (response.isSuccessful) {
                    response.body()?.let { retrievedChecklist ->
                        _checklist.emit(retrievedChecklist)
                        _tasks.addAll(_checklist.value.tasks.toMutableStateList())
                    }
                } else {
                    Log.e("Error", "Error getting response from API")
                }
            }
        } catch (err: Exception) {
            Log.e("Error", err.stackTraceToString())
        }
    }

    // TODO Check server response to close AddChecklistView
    fun publishChecklist() = viewModelScope.launch {
        try {
            val response = api.postChecklist(_checklist.value)

            if (response.isSuccessful) {
                _shouldReturn.emit(true)
            } else {
                _shouldReturn.emit(true)
            }
        } catch (err: Exception) {
            Log.e("Exception", err.stackTraceToString())
        }
    }

    fun changeTitle(newTitle: String) = viewModelScope.launch {
        _checklist.emit(
            Checklist(
                topicId = topicId.value,
                name = newTitle,
                tasks = _tasks
            )
        )
    }

    // Local methods
    fun addNewTask(task: ChecklistTask) {
        _tasks.add(task)
    }

    fun removeTask(task: ChecklistTask) {
        _tasks.remove(task)
    }

    fun modifyTask(taskID: UUID, modifiedTask: ChecklistTask) {
        var index = 0
        var found = false

        while (!found && index < _tasks.size) {
            val task = _tasks[index]
            if (task.taskID == taskID) {
                found = true
            } else {
                index++
            }
        }

        _tasks[index] = _tasks[index].copy(
            description = modifiedTask.description,
            status = modifiedTask.status,
            taskID = modifiedTask.taskID
        )
    }
}