package com.syncstorm.hability.ui.scheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.databinding.FragmentSchedulerBinding
import com.syncstorm.hability.ui.goals.RecyclerAdapterGoals

class SchedulerFragment : Fragment() {

    private lateinit var viewModel: SchedulerViewModel
    private var _binding: FragmentSchedulerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(SchedulerViewModel::class.java)

        _binding = FragmentSchedulerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val addTaskFab = root.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddTaskScheduler)

        addTaskFab.setOnClickListener {
            findNavController().navigate(R.id.nav_creationform)
        }
        viewModel = SchedulerViewModel()
        viewModel.goalsDBScheduler = DatabaseHelper(requireContext())
        viewModel.chipGroupSchedulerDifficultyGoal =
            root.findViewById(R.id.chipGroupSchedulerDifficulty)
        viewModel.recyclerViewScheduler = root.findViewById(R.id.recyclerViewScheduler)


        viewModel.chipGroupSchedulerDifficultyGoal?.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chipSchedulerTrivial -> {


                    viewModel.goalIDScheduler = mutableListOf()
                    viewModel.goalTitleScheduler = mutableListOf()
                    viewModel.goalDescriptionScheduler = mutableListOf()
                    viewModel.goalStartDateScheduler = mutableListOf()
                    viewModel.goalDifficultyScheduler = mutableListOf()
                    viewModel.goalCategoryScheduler = mutableListOf()

                    storeDataIntoListSchedulerDifficultyTrivial()
                    val customAdapterSchedulerTrivial = RecyclerAdapterScheduler(
                        context,
                        viewModel.goalIDScheduler!!,
                        viewModel.goalTitleScheduler!!,
                        viewModel.goalDescriptionScheduler!!,
                        viewModel.goalStartDateScheduler!!,
                        viewModel.goalDifficultyScheduler!!,
                        viewModel.goalCategoryScheduler!!
                    )
                    viewModel.recyclerViewScheduler?.adapter = customAdapterSchedulerTrivial
                    viewModel.recyclerViewScheduler?.layoutManager = LinearLayoutManager(context)

                }
                R.id.chipSchedulerEasy -> {


                    viewModel.goalIDScheduler = mutableListOf()
                    viewModel.goalTitleScheduler = mutableListOf()
                    viewModel.goalDescriptionScheduler = mutableListOf()
                    viewModel.goalStartDateScheduler = mutableListOf()
                    viewModel.goalDifficultyScheduler = mutableListOf()
                    viewModel.goalCategoryScheduler = mutableListOf()

                    storeDataIntoListSchedulerDifficultyEasy()
                    val customAdapterSchedulerEasy = RecyclerAdapterScheduler(
                        context,
                        viewModel.goalIDScheduler!!,
                        viewModel.goalTitleScheduler!!,
                        viewModel.goalDescriptionScheduler!!,
                        viewModel.goalStartDateScheduler!!,
                        viewModel.goalDifficultyScheduler!!,
                        viewModel.goalCategoryScheduler!!
                    )
                    viewModel.recyclerViewScheduler?.adapter = customAdapterSchedulerEasy
                    viewModel.recyclerViewScheduler?.layoutManager = LinearLayoutManager(context)

                }
                R.id.chipSchedulerMedium -> {


                    viewModel.goalIDScheduler = mutableListOf()
                    viewModel.goalTitleScheduler = mutableListOf()
                    viewModel.goalDescriptionScheduler = mutableListOf()
                    viewModel.goalStartDateScheduler = mutableListOf()
                    viewModel.goalDifficultyScheduler = mutableListOf()
                    viewModel.goalCategoryScheduler = mutableListOf()

                    storeDataIntoListSchedulerDifficultyMedium()
                    val customAdapterSchedulerMedium = RecyclerAdapterScheduler(
                        context,
                        viewModel.goalIDScheduler!!,
                        viewModel.goalTitleScheduler!!,
                        viewModel.goalDescriptionScheduler!!,
                        viewModel.goalStartDateScheduler!!,
                        viewModel.goalDifficultyScheduler!!,
                        viewModel.goalCategoryScheduler!!
                    )
                    viewModel.recyclerViewScheduler?.adapter = customAdapterSchedulerMedium
                    viewModel.recyclerViewScheduler?.layoutManager = LinearLayoutManager(context)

                }
                else -> {


                    viewModel.goalIDScheduler = mutableListOf()
                    viewModel.goalTitleScheduler = mutableListOf()
                    viewModel.goalDescriptionScheduler = mutableListOf()
                    viewModel.goalStartDateScheduler = mutableListOf()
                    viewModel.goalDifficultyScheduler = mutableListOf()
                    viewModel.goalCategoryScheduler = mutableListOf()

                    storeDataIntoListSchedulerDifficultyHard()
                    val customAdapterSchedulerHard = RecyclerAdapterScheduler(
                        context,
                        viewModel.goalIDScheduler!!,
                        viewModel.goalTitleScheduler!!,
                        viewModel.goalDescriptionScheduler!!,
                        viewModel.goalStartDateScheduler!!,
                        viewModel.goalDifficultyScheduler!!,
                        viewModel.goalCategoryScheduler!!
                    )
                    viewModel.recyclerViewScheduler?.adapter = customAdapterSchedulerHard
                    viewModel.recyclerViewScheduler?.layoutManager = LinearLayoutManager(context)

                }
            }
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun storeDataIntoListSchedulerDifficultyTrivial() {
        val cursor = viewModel.goalsDBScheduler?.readDataGoalsDifficultyTrivial()

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No Trivial Goals", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                viewModel.goalIDScheduler?.add((cursor.getString(0)))
                viewModel.goalTitleScheduler?.add(cursor.getString(1))
                viewModel.goalDescriptionScheduler?.add(cursor.getString(2))
                viewModel.goalStartDateScheduler?.add(cursor.getString(3))
                viewModel.goalDifficultyScheduler?.add(cursor.getString(4))
                viewModel.goalCategoryScheduler?.add(cursor.getString(5))
            }
        }
    }

    private fun storeDataIntoListSchedulerDifficultyEasy() {
        val cursor = viewModel.goalsDBScheduler?.readDataGoalsDifficultyEasy()

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No Easy Goals", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                viewModel.goalIDScheduler?.add((cursor.getString(0)))
                viewModel.goalTitleScheduler?.add(cursor.getString(1))
                viewModel.goalDescriptionScheduler?.add(cursor.getString(2))
                viewModel.goalStartDateScheduler?.add(cursor.getString(3))
                viewModel.goalDifficultyScheduler?.add(cursor.getString(4))
                viewModel.goalCategoryScheduler?.add(cursor.getString(5))
            }
        }
    }


    private fun storeDataIntoListSchedulerDifficultyMedium() {
        val cursor = viewModel.goalsDBScheduler?.readDataGoalsDifficultyMedium()

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No Medium Goals", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                viewModel.goalIDScheduler?.add((cursor.getString(0)))
                viewModel.goalTitleScheduler?.add(cursor.getString(1))
                viewModel.goalDescriptionScheduler?.add(cursor.getString(2))
                viewModel.goalStartDateScheduler?.add(cursor.getString(3))
                viewModel.goalDifficultyScheduler?.add(cursor.getString(4))
                viewModel.goalCategoryScheduler?.add(cursor.getString(5))
            }
        }
    }


    private fun storeDataIntoListSchedulerDifficultyHard() {
        val cursor = viewModel.goalsDBScheduler?.readDataGoalsDifficultyHard()

        when (cursor?.count) {
            0 -> Toast.makeText(context, "No Hard Goals", Toast.LENGTH_LONG).show()
            else -> while (cursor?.moveToNext() == true) {
                viewModel.goalIDScheduler?.add((cursor.getString(0)))
                viewModel.goalTitleScheduler?.add(cursor.getString(1))
                viewModel.goalDescriptionScheduler?.add(cursor.getString(2))
                viewModel.goalStartDateScheduler?.add(cursor.getString(3))
                viewModel.goalDifficultyScheduler?.add(cursor.getString(4))
                viewModel.goalCategoryScheduler?.add(cursor.getString(5))
            }
        }
    }


}

