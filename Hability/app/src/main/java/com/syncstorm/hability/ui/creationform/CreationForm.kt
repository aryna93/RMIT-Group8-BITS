package com.syncstorm.hability.ui.creationform

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.syncstorm.hability.R
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.syncstorm.hability.database.DatabaseHandler
import com.syncstorm.hability.database.DateTimeModel
import com.syncstorm.hability.database.TaskModelClass

class CreationForm : Fragment() {

    companion object {
        fun newInstance() = CreationForm()
    }

    private lateinit var viewModel: CreationFormViewModel
    lateinit var categorySelected : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.creation_form_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCreationForm : Button = view.findViewById(R.id.buttonCreationForm)
        val editTextTaskTitle : EditText = view.findViewById(R.id.editTextTaskTitle)
        val editTextTaskDescription : EditText = view.findViewById(R.id.editTextTaskDescription)

        val startDate : DatePicker = view.findViewById(R.id.datePickerStart)
        val startTime : TimePicker = view.findViewById(R.id.timePickerStart)
        val endDate : DatePicker = view.findViewById(R.id.datePickerEnd)
        val endTime : TimePicker = view.findViewById(R.id.timePickerEnd)

        val context = requireContext()
        val db = DatabaseHandler(context)
        val taskCategory = resources.getStringArray(R.array.taskCategoryArray)

        val spinner = view.findViewById<Spinner>(R.id.taskCategorySpinner)
        val adapter = ArrayAdapter.createFromResource(
            context,
            R.array.taskCategoryArray,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        fun getSpinnerValue(view: View) {
            categorySelected = spinner.selectedItem.toString()
        }

        btnCreationForm.setOnClickListener{

            getSpinnerValue(view)
            if (editTextTaskTitle.text.isNotEmpty() && editTextTaskDescription.text.isNotEmpty()) {
                val startDateTime = DateTimeModel(
                    startDate.dayOfMonth,
                    startDate.month,
                    startDate.year,
                    startTime.hour,
                    startTime.minute
                )
                val endDateTime = DateTimeModel(
                    endDate.dayOfMonth,
                    endDate.month,
                    endDate.year,
                    endTime.hour,
                    endTime.minute
                )

                var stringStartDate : String =
                    startDateTime.day.toString() + "/" +
                            startDateTime.month.toString() + "/" +
                            startDateTime.year.toString()

                var stringStartTime : String =
                    startDateTime.hour.toString() + ":" +
                            startDateTime.minute.toString()

                var stringEndDate : String =
                    endDateTime.day.toString() + "/" +
                            endDateTime.month.toString() + "/" +
                            endDateTime.year.toString()

                var stringEndTime : String =
                    startDateTime.hour.toString() + ":" +
                            startDateTime.minute.toString()
                val task = TaskModelClass(0,
                    editTextTaskTitle.text.toString(),
                    editTextTaskDescription.text.toString(),
                    "Active",
                    categorySelected,
                    stringStartDate,
                    stringStartTime,
                    stringEndDate,
                    stringEndTime
                )
                db.insertTask(task)
                val navController = findNavController()
                navController.navigate(R.id.nav_calendar)

            } else {
                Toast.makeText(context, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }


    }

}