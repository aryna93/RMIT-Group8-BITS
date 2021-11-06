package com.syncstorm.hability.ui.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoalsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Goals"
    }
    val text: LiveData<String> = _text
}