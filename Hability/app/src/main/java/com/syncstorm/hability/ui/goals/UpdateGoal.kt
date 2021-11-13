//package com.syncstorm.hability.ui.goals
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import com.syncstorm.hability.R
//import com.syncstorm.hability.ui.goals.GoalsFragment
//import com.syncstorm.hability.ui.goals.GoalsViewModel
//
//class UpdateGoal : Fragment() {
//
//    private lateinit var title: String
//    private lateinit var description: String
//    private lateinit var start_date: String
//    private lateinit var end_date: String
//    private lateinit var time: String
//    private lateinit var category: String
//
//    private lateinit var editTextUpdateTitle: EditText
//    private lateinit var editTextUpdateDescription: EditText
//    private lateinit var editTextUpdateStartDate: EditText
//    private lateinit var editTextUpdateEndDate: EditText
//    private lateinit var editTextUpdateTime: EditText
//    private lateinit var editTextUpdateCategory: EditText
//
//    companion object {
//        fun newInstance() = GoalsFragment()
//    }
//
//    private lateinit var viewModel: GoalsViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val v = inflater.inflate(R.layout.fragment_update_goal, container, false)
//
//
//        editTextUpdateTitle = v.findViewById(R.id.editTextUpdateGoalTitle)
//        editTextUpdateDescription = v.findViewById(R.id.editTextUpdateGoalDescription)
//        editTextUpdateStartDate = v.findViewById(R.id.editTextUpdateStartDate)
//        editTextUpdateEndDate = v.findViewById(R.id.editTextUpdateEndDate)
//        editTextUpdateTime = v.findViewById(R.id.editTextUpdateGoalTime)
//        editTextUpdateCategory = v.findViewById(R.id.editTextUpdateGoalTag)
//        //val buttonUpdate = v.findViewById<Button>(R.id.buttonUpdateGoal)
////        getAndSetIntentData()
//
//return v
//    }
//
//
////    private fun getAndSetIntentData() {
////
////        if (LayoutI"title")) {
////            // Getting data from Intent
////
////            title = intent.getStringExtra("title").toString()
////            description = intent.getStringExtra("description").toString()
////            start_date = intent.getStringExtra("start_date").toString()
////            end_date = intent.getStringExtra("end_date").toString()
////            time = intent.getStringExtra("time").toString()
////            category = intent.getStringExtra("category").toString()
////
////            // Setting data to Intent
////            editTextUpdateTitle.setText(title)
////            editTextUpdateDescription.setText(description)
////            editTextUpdateStartDate.setText(start_date)
////            editTextUpdateEndDate.setText(end_date)
////            editTextUpdateTime.setText(time)
////            editTextUpdateCategory.setText(category)
////
////        } else {
////            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
////        }
////    }
//}