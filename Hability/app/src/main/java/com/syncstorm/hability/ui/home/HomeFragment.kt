package com.syncstorm.hability.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.databinding.FragmentHomeBinding

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