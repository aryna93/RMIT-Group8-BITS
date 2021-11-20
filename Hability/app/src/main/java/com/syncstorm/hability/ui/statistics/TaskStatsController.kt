package com.syncstorm.hability.ui.statistics

import com.syncstorm.hability.R
import com.syncstorm.hability.database.TaskModelClass
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskStatsController() {
    fun getSumTaskCategories(taskCategory: MutableList<TaskModelClass>) : TaskCategoryStatsModel {
        var general = 0
        var studying = 0
        var reading = 0
        var leisure = 0
        var gym = 0
        var cooking = 0
        var business = 0
        for (i in taskCategory.indices) {
            when (taskCategory[i].taskCategory) {
                "General" -> ++general
                "Studying" -> ++studying
                "Reading" -> ++reading
                "Leisure" -> ++leisure
                "Gym" -> ++gym
                "Cooking" -> ++cooking
                "Business" -> ++business
            }
        }
        return TaskCategoryStatsModel(general, studying, reading, leisure, gym, cooking, business)
    }

    fun getDaySumTaskCategories(taskCategory: MutableList<TaskModelClass>) : TaskCategoryStatsModel {
        val today = LocalDateTime.now()
        val todayDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        var general = 0
        var studying = 0
        var reading = 0
        var leisure = 0
        var gym = 0
        var cooking = 0
        var business = 0
        for (i in taskCategory.indices) {
            if (taskCategory[i].taskStartDate == todayDate) {
                when (taskCategory[i].taskCategory) {
                    "General" -> ++general
                    "Studying" -> ++studying
                    "Reading" -> ++reading
                    "Leisure" -> ++leisure
                    "Gym" -> ++gym
                    "Cooking" -> ++cooking
                    "Business" -> ++business
                }
            }
        }
        return TaskCategoryStatsModel(general, studying, reading, leisure, gym, cooking, business)
    }
}