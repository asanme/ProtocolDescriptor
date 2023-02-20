package com.asanme.protocoldescriptor.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.entity.ChecklistTask
import java.util.*

class ChecklistViewModel(
    topicId: String,
    private val api: RetrofitAPI
) : ViewModel() {
    private val _tasks = mutableStateListOf<ChecklistTask>()
    val tasks: MutableList<ChecklistTask> = _tasks

    fun addNewTask(task: ChecklistTask) {
        _tasks.add(task)
    }

    fun removeTask(task: ChecklistTask) {
        _tasks.remove(task)
    }

    fun modifyName(taskID: UUID, name: String) {
        _tasks.filter { task -> task.taskID == taskID }.map { task -> task.name = name }
    }

    fun modifyDescription(taskID: UUID, description: String) {
        _tasks.filter { task -> task.taskID == taskID }
            .map { task -> task.description = description }
    }

    fun modifyStatus(taskID: UUID, status: String) {
        _tasks.filter { task -> task.taskID == taskID }.map { task -> task.status = status }
    }
}