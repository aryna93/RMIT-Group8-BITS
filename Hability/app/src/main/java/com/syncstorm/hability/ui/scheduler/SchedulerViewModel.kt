package com.syncstorm.hability.ui.scheduler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.ui.goals.RecyclerAdapterGoals

class SchedulerViewModel : ViewModel() {

    var recyclerViewScheduler: RecyclerView? = null
    var RecyclerAdapterScheduler: RecyclerAdapterGoals? = null
}