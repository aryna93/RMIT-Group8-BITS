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
 * Use the [CalWeekFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalWeekFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_cal_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewWeek)
        val context = requireContext()
        val db = DatabaseHandler(context)
        val data = db.readTask()
        val getStartEndDate: DateTimeHelper.StartEndDate = DateTimeHelper.getCurrentWeekStartEndDate()
        val startWeekDate = getStartEndDate.startDate.toString(DateTimeFormat.forPattern("dd/MM/yyyy"))
        val endWeekDate = getStartEndDate.endDate.toString(DateTimeFormat.forPattern("dd/MM/yyyy"))
        val textViewTodayDate: TextView = view.findViewById(R.id.textViewWeekStartEndDate)
        textViewTodayDate.text = "This week: " + startWeekDate + "  |  " + endWeekDate
        val calC = CalendarController()
        val weekDates: MutableList<String> = calC.datesBetweeen(getStartEndDate.startDate, getStartEndDate.endDate)

        val allTasks: MutableList<TaskModelClass> = data
        val weekTasks: MutableList<TaskModelClass> = ArrayList()

        for (i in allTasks.indices) {
            for (i in 0 until weekDates.size) {
                if (allTasks[i].taskStartDate == weekDates[i]) {
                    val weekTask = TaskModelClass()
                    weekTask.taskID = allTasks[i].taskID
                    weekTask.taskName = allTasks[i].taskName
                    weekTask.taskDescription = allTasks[i].taskDescription
                    weekTask.taskStatus = allTasks[i].taskStatus
                    weekTask.taskCategory = allTasks[i].taskCategory
                    weekTask.taskStartDate = allTasks[i].taskStartDate
                    weekTask.taskStartTime = allTasks[i].taskStartTime
                    weekTask.taskEndDate  = allTasks[i].taskEndDate
                    weekTask.taskEndTime = allTasks[i].taskEndTime
                    weekTasks.add(weekTask)
                }
            }
        }

        val adapter = CalWeekAdapter(weekTasks)
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
         * @return A new instance of fragment CalWeekFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalWeekFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}