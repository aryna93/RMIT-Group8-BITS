package com.syncstorm.hability.ui.goals

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.syncstorm.hability.database.DatabaseHelper

@SuppressLint("StaticFieldLeak")
class GoalsViewModel : ViewModel() {

    // Goals Fragment
    var goalsDB: DatabaseHelper? = null
    var recyclerView: RecyclerView? = null
    var goalID: MutableList<String>? = null
    var goalTitle: MutableList<String>? = null
    var goalDescription: MutableList<String>? = null
    var goalStartDate: MutableList<String>? = null
    var goalDifficulty: MutableList<String>? = null
    var goalCategory: MutableList<String>? = null
    var goalFab: FloatingActionButton? = null

    // Add Goal
    var editTextAddTitleGoal: EditText? = null
    var editTextAddDescriptionGoal: EditText? = null
    var textViewAddStartDateGoal: TextView? = null
    var chipGroupAddDifficultyGoal: ChipGroup? = null
    var editTextAddCategoryGoal: EditText? = null
    var buttonCreateGoal: Button? = null

    // Update/Delete Goal
    var editTextUpdateTitleGoal: EditText? = null
    var editTextUpdateDescriptionGoal: EditText? = null
    var textViewUpdateStartDateGoal: TextView? = null
    var chipGroupUpdateDifficultyGoal: ChipGroup? = null
    var chipUpdateTrivialGoal: Chip? = null
    var chipUpdateEasyGoal: Chip? = null
    var chipUpdateMediumGoal: Chip? = null
    var chipUpdateHardGoal: Chip? = null
    var editTextUpdateCategoryGoal: EditText? = null
    var buttonUpdateGoal: Button? = null
    var buttonDeleteGoal: Button? = null

    var id: String? = null
    var title: String? = null
    var description: String? = null
    var startDate: String? = null
    var difficulty: String? = null
    var category: String? = null

    // Achievement Dialog Fragment
    var dialogFragmentAchievementGoal: AchievementGoal? = null

    // Delete Dialog Fragment
    var dialogFragmentDeleteGoal: AlertDialog.Builder? = null
}