package com.syncstorm.hability.ui.calendar


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class FragmentAdapterCal(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(p0: Int): Fragment {
        when (p0) {
            1 -> return CalWeekFragment()
            2 -> return CalMonthFragment()
            else -> return CalDayFragment()

        }
    }
}