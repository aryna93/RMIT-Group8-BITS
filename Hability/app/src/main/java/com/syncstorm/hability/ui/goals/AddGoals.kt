package com.syncstorm.hability.ui.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper

class AddGoals : Fragment() {

    private lateinit var viewModel: GoalsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_goals, container, false)

        viewModel = GoalsViewModel()
        viewModel.editTextTitle = view.findViewById(R.id.editTextGoalTitle)
        viewModel.editTextDescription = view.findViewById(R.id.editTextGoalDescription)
        viewModel.editTextStartDate = view.findViewById(R.id.editTextStartDate)
        viewModel.editTextEndDate = view.findViewById(R.id.editTextEndDate)
        viewModel.editTextTime = view.findViewById(R.id.editTextGoalTime)
        viewModel.editTextCategory = view.findViewById(R.id.editTextGoalTag)
        viewModel.buttonCreate = view.findViewById(R.id.buttonCreateGoal)

        viewModel.buttonCreate?.setOnClickListener {
            viewModel.goalsDB = DatabaseHelper(requireContext())
            viewModel.goalsDB?.addDataTasks(
                viewModel.editTextTitle?.text.toString().trim(),
                viewModel.editTextDescription?.text.toString().trim(),
                viewModel.editTextStartDate?.text.toString().trim(),
                viewModel.editTextEndDate?.text.toString().trim(),
                viewModel.editTextTime?.text.toString().trim(),
                viewModel.editTextCategory?.text.toString().trim()
            )
            findNavController().navigate(R.id.nav_goals)
        }
        return view
    }

}