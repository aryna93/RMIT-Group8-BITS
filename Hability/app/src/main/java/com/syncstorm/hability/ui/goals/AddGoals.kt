//package com.syncstorm.hability.ui.goals
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import com.syncstorm.hability.R
//import com.syncstorm.hability.database.DatabaseHelper
//
//class AddGoals : Fragment() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        val v = inflater.inflate(R.layout.fragment_add_goals, container, false)
//
//        val editTextTitle = v.findViewById<EditText>(R.id.editTextGoalTitle)
//        val editTextDescription = v.findViewById<EditText>(R.id.editTextGoalDescription)
//        val editTextStartDate = v.findViewById<EditText>(R.id.editTextStartDate)
//        val editTextEndDate = v.findViewById<EditText>(R.id.editTextEndDate)
//        val editTextTime = v.findViewById<EditText>(R.id.editTextGoalTime)
//        val editTextCategory = v.findViewById<EditText>(R.id.editTextGoalTag)
//        val buttonCreate = v.findViewById<Button>(R.id.buttonCreateGoal)
//
//        buttonCreate.setOnClickListener{
//            val habilityDB = DatabaseHelper(requireActivity())
//            habilityDB.addData(editTextTitle.text.toString().trim(), editTextDescription.text.toString().trim(), editTextStartDate.text.toString().trim(), editTextEndDate.text.toString().trim(), editTextTime.text.toString().trim(), editTextCategory.text.toString().trim())
//
//        }
//        return v
//    }
//
//
//}