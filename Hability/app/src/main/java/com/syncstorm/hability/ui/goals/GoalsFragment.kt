package com.syncstorm.hability.ui.goals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.syncstorm.hability.R
import com.syncstorm.hability.databinding.GoalsFragmentBinding


class GoalsFragment : Fragment() {

    private lateinit var goalsViewModel: GoalsViewModel
    private var _binding: GoalsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        goalsViewModel =
            ViewModelProvider(this).get(GoalsViewModel::class.java)

        _binding = GoalsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textGoals
        goalsViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        goalsViewModel = ViewModelProvider(this).get(goalsViewModel::class.java)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}