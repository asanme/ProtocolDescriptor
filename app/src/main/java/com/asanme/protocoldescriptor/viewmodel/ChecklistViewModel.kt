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
    private val checklistId: String? = null,
    private val topicId: String,
    private val api: RetrofitAPI
) : ViewModel() {
    private var _tasks = mutableStateListOf<ChecklistTask>()
    val tasks: MutableList<ChecklistTask> = _tasks

    private var _checklist =
        MutableStateFlow(Checklist(topicId = topicId, name = "", tasks = _tasks))
    val checklist = _checklist.asStateFlow()

    fun retrieveTask() = viewModelScope.launch {
        try {
            if (checklistId != null) {
                val response = api.getChecklist(topicId, checklistId)
                if (response.isSuccessful) {
                    response.body()?.let { retrievedChecklist ->
                        _checklist.emit(retrievedChecklist)
                        _tasks = _checklist.value.tasks.toMutableStateList()
                    }
                } else {
                    Log.e("Error", "Error getting response from API")
                }
            }
        } catch (err: Exception) {
            Log.e("Error", err.stackTraceToString())
        }
    }

    fun publishChecklist() = viewModelScope.launch {
        try {
            api.postChecklist(_checklist.value)
        } catch (err: Exception) {
            Log.e("Exception", err.stackTraceToString())
        }
    }

    fun changeTitle(newTitle: String) = viewModelScope.launch {
        _checklist.emit(
            Checklist(
                topicId = topicId,
                name = newTitle,
                tasks = _tasks
            )
        )
    }

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