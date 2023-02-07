package com.asanme.protocoldescriptor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.protocoldescriptor.model.entity.ProtocolTask

class ProtocolViewModel : ViewModel() {
    private val _currentTasks = MutableLiveData<ProtocolTask>()
    val currentTasks: LiveData<ProtocolTask> = _currentTasks

    fun AddNewTask() {

    }
}