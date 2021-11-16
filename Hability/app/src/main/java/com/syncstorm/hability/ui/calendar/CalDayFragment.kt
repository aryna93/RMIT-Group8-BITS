package com.syncstorm.hability.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHandler
import com.syncstorm.hability.database.TaskModelClass
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalDayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalDayFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cal_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)
        val context = requireContext()
        val db = DatabaseHandler(context)
        val data = db.readTask()

        val today = LocalDateTime.now()
        val todayDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        val allTasks: List<TaskModelClass> = data
        val todayTasks: MutableList<TaskModelClass> = ArrayList()
        for (i in 0 until allTasks.size) {
            if (allTasks[i].taskStartDate == todayDate) {
                val todayTask = TaskModelClass()
                todayTask.taskID = allTasks[i].taskID
                todayTask.taskName = allTasks[i].taskName
                todayTask.taskDescription = allTasks[i].taskDescription
                todayTask.taskStatus = allTasks[i].taskStatus
                todayTask.taskCategory = allTasks[i].taskCategory
                todayTask.taskStartDate = allTasks[i].taskStartDate
                todayTask.taskStartTime = allTasks[i].taskStartTime
                todayTask.taskEndDate  = allTasks[i].taskEndDate
                todayTask.taskEndTime = allTasks[i].taskEndTime
                todayTasks.add(todayTask)
            }
        }

        val adapter = CalDayAdapter(todayTasks)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalDayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalDayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}