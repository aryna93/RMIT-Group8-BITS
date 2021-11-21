package com.syncstorm.hability.ui.scheduler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.ui.goals.RecyclerAdapterGoals

class SchedulerViewModel : ViewModel() {

    var goalsDBScheduler: DatabaseHelper? = null
    var recyclerViewScheduler: RecyclerView? = null
    var goalIDScheduler: MutableList<String>? = null
    var goalTitleScheduler: MutableList<String>? = null
    var goalDescriptionScheduler: MutableList<String>? = null
    var goalStartDateScheduler: MutableList<String>? = null
    var goalDifficultyScheduler: MutableList<String>? = null
    var goalCategoryScheduler: MutableList<String>? = null

    var chipGroupSchedulerDifficultyGoal: ChipGroup? = null

    var idScheduler: String? = null
    var titleScheduler: String? = null
    var descriptionScheduler: String? = null
    var startDateScheduler: String? = null
    var difficultyScheduler: String? = null
    var categoryScheduler: String? = null
}