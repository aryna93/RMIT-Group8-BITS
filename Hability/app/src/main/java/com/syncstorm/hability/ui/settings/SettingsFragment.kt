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

    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var habilityDB: DatabaseHelper
    private lateinit var userName: MutableList<String>
    private lateinit var userEmail: MutableList<String>
    private var _binding: SettingsFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        val root = binding.root


        val editTextUserName = root.findViewById<EditText>(R.id.editTextName)
        val editTextEmailAddress = root.findViewById<EditText>(R.id.editTextEmailAddress)
        val saveButton = root.findViewById<Button>(R.id.buttonSettingsSave)



        saveButton.setOnClickListener {
            val habilityDB = DatabaseHelper(root.context)
            habilityDB.addDataUserDetails(
                editTextUserName.text.toString().trim(),
                editTextEmailAddress.text.toString().trim()
            )
        }

        habilityDB = DatabaseHelper(root.context)
        userName = mutableListOf()
        userEmail = mutableListOf()
        storeDataIntoText()
        if (userName.isNotEmpty() && userEmail.isNotEmpty()) {
            editTextUserName.setText(userName.last())
            editTextEmailAddress.setText(userEmail.last())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun storeDataIntoText() {
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