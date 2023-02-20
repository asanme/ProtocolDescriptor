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
    private var _tasks = mutableStateListOf<ChecklistTask>()
    val tasks: MutableList<ChecklistTask> = _tasks

    fun addNewTask(task: ChecklistTask) {
        _tasks.add(task)
    }

    fun removeTask(task: ChecklistTask) {
        _tasks.remove(task)
    }

    fun modifyTask(taskID: UUID, modifiedTask: ChecklistTask) {
        var index = 0
        var found = false
        for (task in _tasks) {
            if (task.taskID != taskID && !found) {
                index++
            } else {
                found = true
            }
        }

        _tasks[index] = _tasks[index].copy(
            name = modifiedTask.name,
            description = modifiedTask.description,
            status = modifiedTask.status,
            taskID = modifiedTask.taskID
        )
    }
}