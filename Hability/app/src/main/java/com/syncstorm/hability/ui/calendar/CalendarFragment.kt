package com.syncstorm.hability.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.syncstorm.hability.R
import com.syncstorm.hability.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {

    private lateinit var calendarViewModel: CalendarViewModel
    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calendarViewModel =
            ViewModelProvider(this).get(CalendarViewModel::class.java)

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // removed the text view from the XML
//        val textView: TextView = binding.textCalendar
//        calendarViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//
//        })

        val tabLayoutCal = root.findViewById<TabLayout>(R.id.tab_layout_cal)
        val viewPager2Cal = root.findViewById<ViewPager2>(R.id.view_pager2_cal)
        val addTaskFab = root.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddTask)

        var adapter = FragmentAdapterCal(childFragmentManager, lifecycle)
        viewPager2Cal.adapter = adapter

        tabLayoutCal.addTab(tabLayoutCal.newTab().setText("Day"))
        tabLayoutCal.addTab(tabLayoutCal.newTab().setText("Week"))
        tabLayoutCal.addTab(tabLayoutCal.newTab().setText("Month"))

        addTaskFab.setOnClickListener {
            findNavController().navigate(R.id.nav_creationform)
        }

        tabLayoutCal.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab : TabLayout.Tab)
            {
                viewPager2Cal.currentItem = tab.position
            }

            override fun onTabUnselected(tab : TabLayout.Tab)
            {

            }

            override fun onTabReselected(tab : TabLayout.Tab)
            {

            }

        })

        viewPager2Cal.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int)
            {
                tabLayoutCal.selectTab(tabLayoutCal.getTabAt(position))
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}