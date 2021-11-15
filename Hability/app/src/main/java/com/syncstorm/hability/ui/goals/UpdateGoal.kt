package com.syncstorm.hability.ui.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper

class UpdateGoal : Fragment() {

    private lateinit var viewModel: GoalsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_goal, container, false)

        viewModel = GoalsViewModel()
        viewModel.editTextUpdateTitle = view.findViewById(R.id.editTextUpdateGoalTitle)
        viewModel.editTextUpdateDescription = view.findViewById(R.id.editTextUpdateGoalDescription)
        viewModel.editTextUpdateStartDate = view.findViewById(R.id.editTextUpdateStartDate)
        viewModel.editTextUpdateEndDate = view.findViewById(R.id.editTextUpdateEndDate)
        viewModel.editTextUpdateTime = view.findViewById(R.id.editTextUpdateGoalTime)
        viewModel.editTextUpdateCategory = view.findViewById(R.id.editTextUpdateGoalTag)
        viewModel.buttonUpdate = view.findViewById(R.id.buttonUpdateGoal)

        getAndSetData()
        viewModel.buttonUpdate?.setOnClickListener {
            viewModel.goalsDB = DatabaseHelper(requireContext())
            viewModel.goalsDB?.updateDataGoals(
                viewModel.id.toString().trim(),
                viewModel.title.toString().trim(),
                viewModel.description.toString().trim(),
                viewModel.startDate.toString().trim(),
                viewModel.endDate.toString().trim(),
                viewModel.time.toString().trim(),
                viewModel.category.toString().trim()
            )
            findNavController().navigate(R.id.nav_goals)
        }



        return view
    }

    private fun getAndSetData() {

        // Get data
        viewModel.id = arguments?.getString("id").toString()
        viewModel.title = arguments?.getString("title").toString()
        viewModel.description = arguments?.getString("description").toString()
        viewModel.startDate = arguments?.getString("startDate").toString()
        viewModel.endDate = arguments?.getString("endDate").toString()
        viewModel.time = arguments?.getString("time").toString()
        viewModel.category = arguments?.getString("category").toString()

        // Set data
        viewModel.editTextUpdateTitle?.setText(viewModel.title)
        viewModel.editTextUpdateDescription?.setText(viewModel.description)
        viewModel.editTextUpdateStartDate?.setText(viewModel.startDate)
        viewModel.editTextUpdateEndDate?.setText(viewModel.endDate)
        viewModel.editTextUpdateTime?.setText(viewModel.time)
        viewModel.editTextUpdateCategory?.setText(viewModel.category)
    }

}