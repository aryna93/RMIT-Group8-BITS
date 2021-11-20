package com.syncstorm.hability.ui.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.syncstorm.hability.R

@SuppressLint("StaticFieldLeak")
class StatisticsViewModel : ViewModel() {

    var tabLayoutStats: TabLayout? = null
    var viewPager2Stats: ViewPager2? = null
    var adapterStats: FragmentAdapterStats? = null
}