package com.syncstorm.hability.ui.scheduler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SchedulerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Scheduler"
    }
    val text: LiveData<String> = _text
}