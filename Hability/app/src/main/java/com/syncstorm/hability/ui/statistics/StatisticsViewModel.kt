package com.syncstorm.hability.ui.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.tabs.TabLayout
import com.syncstorm.hability.R
import com.syncstorm.hability.database.DatabaseHelper

@SuppressLint("StaticFieldLeak")
class StatisticsViewModel : ViewModel() {

    var tabLayoutStats: TabLayout? = null
    var adapterStats: FragmentAdapterStats? = null
    var goalsDB: DatabaseHelper? = null
    var pieChartGoals : PieChart? = null
    var pieChartVisitors : MutableList<PieEntry>? = null
    var difficultyDataSet: PieDataSet? = null
    var difficultyData: PieData? = null

}