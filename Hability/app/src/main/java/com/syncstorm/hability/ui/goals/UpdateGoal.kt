package com.syncstorm.hability.ui.goals

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import java.util.*

class UpdateGoal : Fragment(), DatePickerDialog.OnDateSetListener {

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
        viewModel.dialogFragmentDeleteGoal = AlertDialog.Builder(requireContext())
        viewModel.editTextUpdateTitleGoal = view.findViewById(R.id.editTextTitleGoalUpdate)
        viewModel.editTextUpdateDescriptionGoal =
            view.findViewById(R.id.editTextDescriptionGoalUpdate)
        viewModel.textViewUpdateStartDateGoal = view.findViewById(R.id.textViewStartDateGoalUpdate)
        viewModel.chipGroupUpdateDifficultyGoal =
            view.findViewById(R.id.chipGroupDifficultyGoalUpdate)
        viewModel.chipUpdateTrivialGoal = view.findViewById(R.id.chipDifficultyTrivialGoalUpdate)
        viewModel.chipUpdateEasyGoal = view.findViewById(R.id.chipDifficultyEasyGoalUpdate)
        viewModel.chipUpdateMediumGoal = view.findViewById(R.id.chipDifficultyMediumGoalUpdate)
        viewModel.chipUpdateHardGoal = view.findViewById(R.id.chipDifficultyHardGoalUpdate)
        viewModel.editTextUpdateCategoryGoal = view.findViewById(R.id.editTextCategoryGoalUpdate)
        viewModel.buttonUpdateGoal = view.findViewById(R.id.buttonUpdateGoal)
        viewModel.buttonDeleteGoal = view.findViewById(R.id.buttonDeleteGoal)

        getAndSetData()

        viewModel.textViewUpdateStartDateGoal?.setOnClickListener {
            showDatePickerDialog()
        }

        viewModel.buttonUpdateGoal?.setOnClickListener {
            viewModel.goalsDB = DatabaseHelper(requireContext())
            viewModel.goalsDB?.updateDataGoals(
                viewModel.id.toString().trim(),
                viewModel.editTextUpdateTitleGoal?.text.toString().trim(),
                viewModel.editTextUpdateDescriptionGoal?.text.toString().trim(),
                viewModel.textViewUpdateStartDateGoal?.text.toString().trim(),
                viewModel.chipGroupUpdateDifficultyGoal?.findViewById<Chip>(viewModel.chipGroupUpdateDifficultyGoal!!.checkedChipId)?.text.toString()
                    .trim(),
                viewModel.editTextUpdateCategoryGoal?.text.toString().trim()
            )
            findNavController().navigate(R.id.nav_goals)
        }

        viewModel.buttonDeleteGoal?.setOnClickListener {
            showConfirmDeleteDialog()
        }
        return view
    }

    private fun getAndSetData() {

        // Get data
        viewModel.id = arguments?.getString("id").toString()
        viewModel.title = arguments?.getString("title").toString()
        viewModel.description = arguments?.getString("description").toString()
        viewModel.startDate = arguments?.getString("startDate").toString()
        viewModel.difficulty = arguments?.getString("difficulty").toString()
        viewModel.category = arguments?.getString("category").toString()

        // Set data
        viewModel.editTextUpdateTitleGoal?.setText(viewModel.title)
        viewModel.editTextUpdateDescriptionGoal?.setText(viewModel.description)
        viewModel.textViewUpdateStartDateGoal?.text = viewModel.startDate
        when (viewModel.difficulty) {
            "Trivial" -> viewModel.chipUpdateTrivialGoal!!.isChecked = true
            "Easy" -> viewModel.chipUpdateEasyGoal!!.isChecked = true
            "Medium" -> viewModel.chipUpdateMediumGoal!!.isChecked = true
            "Hard" -> viewModel.chipUpdateHardGoal!!.isChecked = true
        }
        viewModel.editTextUpdateCategoryGoal?.setText(viewModel.category)
    }


    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.textViewUpdateStartDateGoal?.text = "$dayOfMonth/${month+1}/$year"
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

    private fun showConfirmDeleteDialog() {
        viewModel.dialogFragmentDeleteGoal?.setMessage("Are you sure you want to delete?")
            ?.setPositiveButton("Confirm") { _, _ ->
                viewModel.goalsDB = DatabaseHelper(requireContext())
                viewModel.goalsDB?.deleteDataGoals(viewModel.id.toString().trim())
                findNavController().navigate(R.id.nav_goals)
            }?.setNegativeButton("Cancel") { _, _ -> }
        viewModel.dialogFragmentDeleteGoal?.create()?.show()
    }
}