package com.syncstorm.hability.ui.statistics

import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.formatter.PercentFormatter





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
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = ""

        val typeAmountMap: MutableMap<String, Int> = HashMap()
        typeAmountMap["General"] = sumTaskCategories.general
        typeAmountMap["Business"] = sumTaskCategories.business
        typeAmountMap["Cooking"] = sumTaskCategories.cooking
        typeAmountMap["Gym"] = sumTaskCategories.gym
        typeAmountMap["Leisure"] = sumTaskCategories.leisure
        typeAmountMap["Studying"] = sumTaskCategories.studying
        typeAmountMap["Reading"] = sumTaskCategories.reading

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#304567"))
        colors.add(Color.parseColor("#309967"))
        colors.add(Color.parseColor("#476567"))
        colors.add(Color.parseColor("#890567"))
        colors.add(Color.parseColor("#a35567"))
        colors.add(Color.parseColor("#ff5f67"))
        colors.add(Color.parseColor("#3ca567"))

        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
        }

        val pieDataSet = PieDataSet(pieEntries, label)
        pieDataSet.setValueFormatter(PercentFormatter())
        pieDataSet.valueTextSize = 12f
        pieDataSet.colors = colors
        pieChart.description.isEnabled = false
        pieChart.setUsePercentValues(true)

        pieChart.setHighlightPerTapEnabled(true)

        val pieData = PieData(pieDataSet)

        pieData.setDrawValues(true)

        pieChart.data = pieData
        pieChart.invalidate()
    }
}