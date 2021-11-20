package com.syncstorm.hability.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHandler
import com.syncstorm.hability.database.TaskModelClass
import org.joda.time.format.DateTimeFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalMonthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalMonthFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cal_month, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewMonth)
        val context = requireContext()
        val db = DatabaseHandler(context)
        val data = db.readTask()
        val getStartEndDate: DateTimeHelper.StartEndDate =
            DateTimeHelper.getCurrentMonthStartEndDate()
        val startMonthDate =
            getStartEndDate.startDate.toString(DateTimeFormat.forPattern("dd/MM/yyyy"))
        val endMonthDate = getStartEndDate.endDate.toString(DateTimeFormat.forPattern("dd/MM/yyyy"))
        val textViewTodayDate: TextView = view.findViewById(R.id.textViewMonthStartEndDate)
        textViewTodayDate.text = "This week: " + startMonthDate + "  |  " + endMonthDate + " || \n"
        val calC = CalendarController(context)
        val monthDates: MutableList<String> =
            calC.DatesBetweeen(getStartEndDate.startDate, getStartEndDate.endDate)

        val allTasks: MutableList<TaskModelClass> = data
        val monthTasks: MutableList<TaskModelClass> = ArrayList()

        for (i in allTasks.indices) {
            for (d in monthDates.indices) {
                if (allTasks[i].taskStartDate == monthDates[d]) {
                    val monthTask = TaskModelClass()
                    monthTask.taskID = allTasks[i].taskID
                    monthTask.taskName = allTasks[i].taskName
                    monthTask.taskDescription = allTasks[i].taskDescription
                    monthTask.taskStatus = allTasks[i].taskStatus
                    monthTask.taskCategory = allTasks[i].taskCategory
                    monthTask.taskStartDate = allTasks[i].taskStartDate
                    monthTask.taskStartTime = allTasks[i].taskStartTime
                    monthTask.taskEndDate = allTasks[i].taskEndDate
                    monthTask.taskEndTime = allTasks[i].taskEndTime
                    monthTasks.add(monthTask)
                }
            }
        }

        val adapter = CalMonthAdapter(monthTasks)
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
         * @return A new instance of fragment CalMonthFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalMonthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}