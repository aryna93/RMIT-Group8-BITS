package com.syncstorm.hability.ui.goals

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.databinding.FragmentAddGoalsBinding
import com.syncstorm.hability.databinding.GoalsFragmentBinding


open class GoalsFragment : Fragment() {


    companion object {
        fun newInstance() = GoalsFragment()
    }

    private lateinit var viewModel: GoalsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.goals_fragment, container, false)
        viewModel = GoalsViewModel()
        viewModel.goalsDB = DatabaseHelper(requireContext())
        viewModel.goalID = mutableListOf()
        viewModel.taskTitle = mutableListOf()
        viewModel.taskDescription = mutableListOf()
        viewModel.taskStartDate = mutableListOf()
        viewModel.taskEndDate = mutableListOf()
        viewModel.taskTime = mutableListOf()
        viewModel.taskCategory = mutableListOf()
        viewModel.recyclerView = view.findViewById(R.id.recyclerViewGoals)
        viewModel.fabGoals = view.findViewById(R.id.floatingActionButtonGoals)

        storeDataIntoList()
        val customAdapter = RecyclerAdapterGoals(
            context,
            viewModel.goalID!!,
            viewModel.taskTitle!!,
            viewModel.taskDescription!!,
            viewModel.taskStartDate!!,
            viewModel.taskEndDate!!,
            viewModel.taskTime!!,
            viewModel.taskCategory!!
        )
        viewModel.recyclerView?.adapter = customAdapter
        viewModel.recyclerView?.layoutManager = LinearLayoutManager(context)


        viewModel.fabGoals?.setOnClickListener{

            findNavController().navigate(R.id.addGoals)
        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GoalsViewModel::class.java)
    }


    private fun storeDataIntoList() {
        val cursor = viewModel.goalsDB?.readDataTasks()
        if (cursor?.count == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor?.moveToNext() == true) {
                viewModel.goalID?.add((cursor.getString(0)))
                viewModel.taskTitle?.add(cursor.getString(1))
                viewModel.taskDescription?.add(cursor.getString(2))
                viewModel.taskStartDate?.add(cursor.getString(3))
                viewModel.taskEndDate?.add(cursor.getString(4))
                viewModel.taskTime?.add(cursor.getString(5))
                viewModel.taskCategory?.add(cursor.getString(6))
            }
        }
    }
}
