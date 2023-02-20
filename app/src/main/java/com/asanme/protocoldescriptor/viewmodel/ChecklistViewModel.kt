package com.asanme.protocoldescriptor.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.asanme.protocoldescriptor.model.RetrofitAPI
import com.asanme.protocoldescriptor.model.entity.ChecklistTask

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

    fun modifyName(index: Int, name: String) {
        _tasks[index].name = name
    }

    fun modifyDescription(index: Int, description: String) {
        _tasks[index].description = description
    }

    fun modifyStatus(index: Int, status: String) {
        _tasks[index].status = status
    }
}

