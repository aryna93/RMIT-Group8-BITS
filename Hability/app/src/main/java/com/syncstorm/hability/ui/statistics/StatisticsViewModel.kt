package com.syncstorm.hability.ui.statistics

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.syncstorm.hability.R

class StatisticsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Statistics"
    }
    val text: LiveData<String> = _text

}