package com.syncstorm.hability.ui.goals

import android.app.AlertDialog
import android.content.DialogInterface
import android.icu.number.Precision.increment
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import kotlin.random.Random


class GoalsFragment : Fragment() {

    companion object {
        fun newInstance() = GoalsFragment()

        private var goalCount: Int = 0
        private var counter: Int = 0
    }

    init {
        goalCount = counter
        counter++
    }

    private lateinit var viewModel: GoalsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.goals_fragment, container, false)
        viewModel = GoalsViewModel()
        viewModel.dialogFragmentAchievementGoal = AchievementGoal()
        viewModel.goalsDB = DatabaseHelper(requireContext())
        viewModel.goalID = mutableListOf()
        viewModel.goalTitle = mutableListOf()
        viewModel.goalDescription = mutableListOf()
        viewModel.goalStartDate = mutableListOf()
        viewModel.goalDifficulty = mutableListOf()
        viewModel.goalCategory = mutableListOf()
        viewModel.recyclerView = view.findViewById(R.id.recyclerViewGoals)
        viewModel.goalFab = view.findViewById(R.id.floatingActionButtonGoals)

        storeDataIntoList()
        displayAchievementGoal()

        val customAdapter = RecyclerAdapterGoals(
            context,
            viewModel.goalID!!,
            viewModel.goalTitle!!,
            viewModel.goalDescription!!,
            viewModel.goalStartDate!!,
            viewModel.goalDifficulty!!,
            viewModel.goalCategory!!
        )
        viewModel.recyclerView?.adapter = customAdapter
        viewModel.recyclerView?.layoutManager = LinearLayoutManager(context)


        viewModel.goalFab?.setOnClickListener {
            findNavController().navigate(R.id.addGoals)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GoalsViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.goal, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_goals -> {
                showConfirmDeleteAllDialog()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun storeDataIntoList() {
        val cursor = viewModel.goalsDB?.readDataGoals()

        when (cursor?.count) {
            0 -> Toast.makeText(context, "Add Goals", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                viewModel.goalID?.add((cursor.getString(0)))
                viewModel.goalTitle?.add(cursor.getString(1))
                viewModel.goalDescription?.add(cursor.getString(2))
                viewModel.goalStartDate?.add(cursor.getString(3))
                viewModel.goalDifficulty?.add(cursor.getString(4))
                viewModel.goalCategory?.add(cursor.getString(5))
            }
        }
    }

    private fun displayAchievementGoal() {
        if (goalCount == 4) {
            viewModel.dialogFragmentAchievementGoal?.show(
                childFragmentManager,
                "Achievement Unlocked"
            )
            counter = 1
        }
    }

    private fun showConfirmDeleteAllDialog() {
        val deleteAllDialog = AlertDialog.Builder(requireContext())
        deleteAllDialog.setMessage("Are you sure you want to delete all?")
            .setPositiveButton("Confirm") { _, _ ->
                viewModel.goalsDB = DatabaseHelper(requireContext())
                viewModel.goalsDB?.deleteDataGoalsAll()
                findNavController().navigate(R.id.nav_goals)
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("Cancel") { _, _ -> }
        deleteAllDialog.create().show()
    }
}

