package com.syncstorm.hability.ui.scheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.databinding.FragmentSchedulerBinding
import com.syncstorm.hability.ui.goals.RecyclerAdapterGoals
import com.syncstorm.hability.ui.scheduler.SchedulerViewModel


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
        val root = binding.root
        viewModel = SchedulerViewModel()
        viewModel.recyclerViewScheduler = root.findViewById(R.id.recyclerViewScheduler)
//        viewModel.RecyclerAdapterScheduler = RecyclerAdapterGoals(
//            context,

//
//        )



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}