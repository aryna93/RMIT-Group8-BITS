package com.syncstorm.hability.ui.settings

import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syncstorm.hability.database.DatabaseHelper
import com.syncstorm.hability.databinding.SettingsFragmentBinding

class SettingsViewModel : ViewModel() {

    var userCredentialDB: DatabaseHelper? = null
    var userName: MutableList<String>? = null
    var userEmail: MutableList<String>? = null


    var editTextUserName: EditText? = null
    var editTextEmailAddress: EditText? = null
    var saveButtonSettings: Button? = null


}