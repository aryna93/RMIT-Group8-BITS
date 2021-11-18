package com.syncstorm.hability.ui.settings

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
import com.syncstorm.hability.databinding.SettingsFragmentBinding


class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel
    private var _binding: SettingsFragmentBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        val root = binding.root
        viewModel = SettingsViewModel()
        viewModel.userCredentialDB = DatabaseHelper(requireContext())
        viewModel.userName = mutableListOf()
        viewModel.userEmail = mutableListOf()
        viewModel.editTextUserName = root.findViewById(R.id.editTextUserName)
        viewModel.editTextEmailAddress = root.findViewById(R.id.editTextEmailAddress)
        viewModel.saveButtonSettings = root.findViewById(R.id.buttonSettingsSave)

        viewModel.saveButtonSettings?.setOnClickListener {
            viewModel.userCredentialDB?.addDataUserCredential(
                viewModel.editTextUserName?.text.toString().trim(),
                viewModel.editTextEmailAddress?.text.toString().trim()
            )
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        }

        storeDataIntoText()
        if (viewModel.userName!!.isNotEmpty() && viewModel.userEmail!!.isNotEmpty()) {
            viewModel.editTextUserName?.setText(viewModel.userName?.last())
            viewModel.editTextEmailAddress?.setText(viewModel.userEmail?.last())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun storeDataIntoText() {
        val cursor = viewModel.userCredentialDB?.readDataUserDetails()
        if (cursor?.count == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor?.moveToNext() == true) {
                viewModel.userName?.add(cursor.getString(0))
                viewModel.userEmail?.add(cursor.getString(1))
            }
        }
    }
}