package com.syncstorm.hability.ui.goals

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.syncstorm.hability.database.DatabaseHelper

@SuppressLint("StaticFieldLeak")
class GoalsViewModel : ViewModel() {

    // Goals Fragment
    var goalsDB: DatabaseHelper? = null
    var recyclerView: RecyclerView? = null
    var goalID: MutableList<String>? = null
    var taskTitle: MutableList<String>? = null
    var taskDescription: MutableList<String>? = null
    var taskStartDate: MutableList<String>? = null
    var taskEndDate: MutableList<String>? = null
    var taskTime: MutableList<String>? = null
    var taskCategory: MutableList<String>? = null
    var fabGoals: FloatingActionButton? = null


    // AddGoals Fragment
    var editTextTitle: EditText? = null
    var editTextDescription: EditText? = null
    var editTextStartDate: EditText? = null
    var editTextEndDate: EditText? = null
    var editTextTime: EditText? = null
    var editTextCategory: EditText? = null
    var buttonCreate: Button? = null

    // UpdateGoal Fragment
    var editTextUpdateTitle: EditText? = null
    var editTextUpdateDescription: EditText? = null
    var editTextUpdateStartDate: EditText? = null
    var editTextUpdateEndDate: EditText? = null
    var editTextUpdateTime: EditText? = null
    var editTextUpdateCategory: EditText? = null
    var buttonUpdate: Button? = null

    var id: String? = null
    var title: String? = null
    var description: String? = null
    var startDate: String? = null
    var endDate: String? = null
    var time: String? = null
    var category: String? = null

}