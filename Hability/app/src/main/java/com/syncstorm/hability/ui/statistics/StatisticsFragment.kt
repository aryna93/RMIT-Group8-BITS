package com.syncstorm.hability.ui.statistics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.syncstorm.hability.R
import kotlin.concurrent.fixedRateTimer

class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.statistics_fragment, container, false)
        val tabLayoutStats = v.findViewById<TabLayout>(R.id.tab_layout_stats)
        val viewPager2Stats = v.findViewById<ViewPager2>(R.id.view_pager2_stats)

        var adapter = FragmentAdapterStats(childFragmentManager, lifecycle)
        viewPager2Stats.adapter = adapter

        tabLayoutStats.addTab(tabLayoutStats.newTab().setText("Tasks"))
        tabLayoutStats.addTab(tabLayoutStats.newTab().setText("Goals"))

        tabLayoutStats.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab : TabLayout.Tab)
            {
                viewPager2Stats.currentItem = tab.position
            }

            override fun onTabUnselected(tab : TabLayout.Tab)
            {

            }

            override fun onTabReselected(tab : TabLayout.Tab)
            {

            }

        })

        viewPager2Stats.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int)
            {
                tabLayoutStats.selectTab(tabLayoutStats.getTabAt(position))
            }

        })

return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

