package com.syncstorm.hability.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHandler
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.database.TaskModelClass
import com.syncstorm.hability.databinding.FragmentHomeBinding
import java.lang.IndexOutOfBoundsException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var habilityDB: DatabaseHelper
    private lateinit var userName: MutableList<String>
    private lateinit var userEmail: MutableList<String>
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textViewUserNameHome = root.findViewById<TextView>(R.id.textViewUserNameHome)
        habilityDB = DatabaseHelper(root.context)
        userName = mutableListOf()
        userEmail = mutableListOf()
        storeDataIntoText()
        if (userName.isNotEmpty() && userEmail.isNotEmpty()) {
            textViewUserNameHome.setText(userName.last())
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Counters
        val tvTaskTodayDoneCount: TextView = view.findViewById(R.id.textViewTaskTodayDoneCount)
        val tvTaskTodayActiveCount: TextView = view.findViewById(R.id.textViewTaskTodayActiveCount)
        val tvGoalActiveCount: TextView = view.findViewById(R.id.textViewGoalActiveCount)

        // Latest Task
        val tvLatestTaskName: TextView = view.findViewById(R.id.textViewLatestTaskName)
        val tvLatestTaskDescription: TextView = view.findViewById(R.id.textViewLatestTaskDescription)
        val tvLatestTaskCategory: TextView = view.findViewById(R.id.textViewLatestTaskCategory)
        val tvLatestTaskStartTime: TextView = view.findViewById(R.id.textViewLatestTaskStartTime)
        val tvLatestTaskEndTime: TextView = view.findViewById(R.id.textViewLatestTaskEndTime)
        val imgLatestTaskCategory: ImageView = view.findViewById(R.id.imageViewLatestTaskCategory)

        // Latest Goal
        val tvLatestGoalName: TextView = view.findViewById(R.id.textViewLatestGoalName)
        val tvLatestGoalCategory: TextView = view.findViewById(R.id.textViewLatestGoalCategory)
        val tvLatestGoalDifficulty: TextView = view.findViewById(R.id.textViewLatestGoalDifficulty)
        val imgLatestGoalDifficulty: ImageView = view.findViewById(R.id.imageViewLatestGoalDifficulty)

        val context = requireContext()
        val homeC = HomeController()

        // Goal
        val dbGoal = DatabaseHelper(context)
        val dataGoals = dbGoal.readDataGoals()

        // Task
        val dbTask = DatabaseHandler(context)
        val dataTasks = dbTask.readTask()

        try {
            val latestTask = homeC.getLatestTask(dataTasks)
            tvLatestTaskName.text = latestTask.taskName
            tvLatestTaskDescription.text = latestTask.taskDescription
            tvLatestTaskCategory.text = latestTask.taskCategory
            tvLatestTaskStartTime.text = latestTask.taskStartTime
            tvLatestTaskEndTime.text = latestTask.taskEndTime

            when (latestTask.taskCategory) {
                "General" -> imgLatestTaskCategory.setImageResource(R.drawable.taskcategory_misc)
                "Studying" -> imgLatestTaskCategory.setImageResource(R.drawable.taskcategory_studying)
                "Reading" -> imgLatestTaskCategory.setImageResource(R.drawable.taskcategory_reading)
                "Leisure" -> imgLatestTaskCategory.setImageResource(R.drawable.taskcategory_leisure)
                "Gym" -> imgLatestTaskCategory.setImageResource(R.drawable.taskcategory_gym)
                "Cooking" -> imgLatestTaskCategory.setImageResource(R.drawable.taskcategory_cooking)
                "Business" -> imgLatestTaskCategory.setImageResource(R.drawable.taskcategory_business)
            }

        } catch (e: IndexOutOfBoundsException) {
            Toast.makeText(context, "No Tasks for Today!", Toast.LENGTH_SHORT).show()
        }

        tvTaskTodayActiveCount.text = homeC.getSumAllTasksToday(dataTasks)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun storeDataIntoText() {
        val cursor = habilityDB.readDataUserDetails()
        if (cursor?.count == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor?.moveToNext() == true) {
                userName.add(cursor.getString(0))
                userEmail.add(cursor.getString(1))
            }
        }
    }
}