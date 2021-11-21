package com.syncstorm.hability.ui.statistics

import android.content.Context
import com.syncstorm.hability.R
import com.syncstorm.hability.database.TaskModelClass
import com.syncstorm.hability.ui.calendar.CalendarController
import com.syncstorm.hability.ui.calendar.DateTimeHelper
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

    fun getMonthSumTaskCategories(taskCategory: MutableList<TaskModelClass>, context: Context) : TaskCategoryStatsModel {

        val getStartEndDate: DateTimeHelper.StartEndDate =
            DateTimeHelper.getCurrentMonthStartEndDate()
        val calC = CalendarController(context)
        val monthDates: MutableList<String> =
            calC.DatesBetweeen(getStartEndDate.startDate, getStartEndDate.endDate)
        val monthTasks: MutableList<TaskModelClass> = ArrayList()
        for (i in taskCategory.indices) {
            for (d in monthDates.indices) {
                if (taskCategory[i].taskStartDate == monthDates[d]) {
                    val monthTask = TaskModelClass()
                    monthTask.taskID = taskCategory[i].taskID
                    monthTask.taskName = taskCategory[i].taskName
                    monthTask.taskDescription = taskCategory[i].taskDescription
                    monthTask.taskStatus = taskCategory[i].taskStatus
                    monthTask.taskCategory = taskCategory[i].taskCategory
                    monthTask.taskStartDate = taskCategory[i].taskStartDate
                    monthTask.taskStartTime = taskCategory[i].taskStartTime
                    monthTask.taskEndDate = taskCategory[i].taskEndDate
                    monthTask.taskEndTime = taskCategory[i].taskEndTime
                    monthTasks.add(monthTask)
                }
            }
        }

        var general = 0
        var studying = 0
        var reading = 0
        var leisure = 0
        var gym = 0
        var cooking = 0
        var business = 0
        for (i in monthTasks.indices) {
                when (monthTasks[i].taskCategory) {
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
}