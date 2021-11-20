package com.syncstorm.hability.ui.statistics

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class StatsChartHelper {
    fun barChart (barChart: BarChart, sumTaskCategories: TaskCategoryStatsModel) {
        val labels = arrayListOf(
            "General",
            "Business",
            "Cooking",
            "Gym",
            "Leisure",
            "Studying",
            "Reading"
        )
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, sumTaskCategories.general.toFloat()))
        entries.add(BarEntry(1f, sumTaskCategories.business.toFloat()))
        entries.add(BarEntry(2f, sumTaskCategories.cooking.toFloat()))
        entries.add(BarEntry(3f, sumTaskCategories.gym.toFloat()))
        entries.add(BarEntry(4f, sumTaskCategories.leisure.toFloat()))
        entries.add(BarEntry(5f, sumTaskCategories.studying.toFloat()))
        entries.add(BarEntry(6f, sumTaskCategories.reading.toFloat()))

        val barDataSet = BarDataSet(entries, "")
        barDataSet.valueTextSize = 12f
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        barChart.data = data

        barChart.axisLeft.setDrawGridLines(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)

        barChart.axisRight.isEnabled = false

        barChart.legend.isEnabled = false

        barChart.description.isEnabled = false

        barChart.animateY(3000)

        barChart.invalidate()
    }

    fun pieChart (pieChart: PieChart, sumTaskCategories: TaskCategoryStatsModel) {

    }
}