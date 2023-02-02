package com.asanme.protocoldescriptor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.protocoldescriptor.model.entity.ProtocolTask

class ProtocolViewModel : ViewModel() {
    private val _currentItems = MutableLiveData<ProtocolTask>()
    val currentItems: LiveData<ProtocolTask> = _currentItems
}