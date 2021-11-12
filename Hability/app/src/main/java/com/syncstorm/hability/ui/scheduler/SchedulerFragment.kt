package com.syncstorm.hability.ui.scheduler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.syncstorm.hability.databinding.FragmentSchedulerBinding
import android.widget.Button
import android.widget.Toast
import com.syncstorm.hability.R

class SchedulerFragment : Fragment(), View.OnClickListener {

    private lateinit var schedulerViewModel: SchedulerViewModel
    private var _binding: FragmentSchedulerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater!!.inflate(R.layout.fragment_scheduler, container, false)
        val btn: Button = view.findViewById(R.id.addTask)
        btn.setOnClickListener(this)


        schedulerViewModel =
            ViewModelProvider(this).get(SchedulerViewModel::class.java)

        _binding = FragmentSchedulerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textView2
        schedulerViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it

        })


        return view
    }

    companion object {
        fun newInstance(): SchedulerFragment {
            return SchedulerFragment()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addTask -> {

             val toastTest = Toast.makeText(requireActivity(), "Hello", Toast.LENGTH_LONG).show()

            }

            else -> {
            }
        }


        fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

}