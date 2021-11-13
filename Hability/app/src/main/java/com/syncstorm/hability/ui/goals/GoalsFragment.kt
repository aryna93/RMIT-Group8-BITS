package com.syncstorm.hability.ui.goals

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper


open class GoalsFragment : Fragment() {

    private lateinit var habilityDB: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskTitle: MutableList<String>
    private lateinit var taskDescription: MutableList<String>
    private lateinit var taskStartDate: MutableList<String>
    private lateinit var taskEndDate: MutableList<String>
    private lateinit var taskTime: MutableList<String>
    private lateinit var taskCategory: MutableList<String>

    companion object {
        fun newInstance() = GoalsFragment()
    }

    private lateinit var viewModel: GoalsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.goals_fragment, container, false)

        habilityDB = DatabaseHelper(v.context)
        taskTitle = mutableListOf()
        taskDescription = mutableListOf()
        taskStartDate = mutableListOf()
        taskEndDate = mutableListOf()
        taskTime = mutableListOf()
        taskCategory = mutableListOf()
        recyclerView = v.findViewById(R.id.recyclerViewGoals)


        storeDataIntoList()
        val customAdapter = RecyclerAdapterGoals(
            this.context,
            taskTitle,
            taskDescription,
            taskStartDate,
            taskEndDate,
            taskTime,
            taskCategory
        )
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)



        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GoalsViewModel::class.java)

    }


    private fun storeDataIntoList() {
        val cursor = habilityDB.readDataTasks()
        if (cursor?.count == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()

        } else {
            while (cursor?.moveToNext() == true) {
                taskTitle.add(cursor.getString(0))
                taskDescription.add(cursor.getString(1))
                taskStartDate.add(cursor.getString(2))
                taskEndDate.add(cursor.getString(3))
                taskTime.add(cursor.getString(4))
                taskCategory.add(cursor.getString(5))

            }
        }
    }
}
