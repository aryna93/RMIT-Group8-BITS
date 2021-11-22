package com.syncstorm.hability.ui.goals

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import java.util.*

class AddGoals : Fragment(), DatePickerDialog.OnDateSetListener {

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
        viewModel.editTextAddTitleGoal = view.findViewById(R.id.editTextTitleGoalAdd)
        viewModel.editTextAddDescriptionGoal = view.findViewById(R.id.editTextDescriptionGoalAdd)
        viewModel.textViewAddStartDateGoal = view.findViewById(R.id.textViewStartDateGoalUpdate)
        viewModel.chipGroupAddDifficultyGoal = view.findViewById(R.id.chipGroupDifficultyGoalAdd)
        viewModel.editTextAddCategoryGoal = view.findViewById(R.id.editTextCategoryGoalAdd)
        viewModel.buttonCreateGoal = view.findViewById(R.id.buttonCreateGoal)

        viewModel.textViewAddStartDateGoal?.setOnClickListener {
            showDatePickerDialog()
        }

        viewModel.buttonCreateGoal?.setOnClickListener {
            viewModel.goalsDB = DatabaseHelper(requireContext())
            viewModel.goalsDB?.addDataGoals(
                viewModel.editTextAddTitleGoal?.text.toString().trim(),
                viewModel.editTextAddDescriptionGoal?.text.toString().trim(),
                viewModel.textViewAddStartDateGoal?.text.toString().trim(),
                viewModel.chipGroupAddDifficultyGoal?.findViewById<Chip>(viewModel.chipGroupAddDifficultyGoal!!.checkedChipId)?.text.toString()
                    .trim(),
                viewModel.editTextAddCategoryGoal?.text.toString().trim(),
            )

            findNavController().navigate(R.id.nav_goals)
        }
        return view
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.textViewAddStartDateGoal?.text = "$dayOfMonth/${month+1}/$year"
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}