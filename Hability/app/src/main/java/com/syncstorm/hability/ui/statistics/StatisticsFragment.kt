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
        val view = inflater.inflate(R.layout.statistics_fragment, container, false)
        val viewPager2Stats = view.findViewById<ViewPager2>(R.id.view_pager2_stats)
        viewModel = StatisticsViewModel()
        viewModel.tabLayoutStats = view.findViewById(R.id.tab_layout_stats)

        viewModel.adapterStats = FragmentAdapterStats(childFragmentManager, lifecycle)
        viewPager2Stats.adapter = viewModel.adapterStats
        viewModel.tabLayoutStats?.addTab(viewModel.tabLayoutStats?.newTab()!!.setText("Tasks"))
        viewModel.tabLayoutStats?.addTab(viewModel.tabLayoutStats?.newTab()!!.setText("Goals"))

        viewModel.tabLayoutStats?.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2Stats.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        viewPager2Stats.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.tabLayoutStats?.selectTab(viewModel.tabLayoutStats?.getTabAt(position))
            }

        })

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
    }
}

