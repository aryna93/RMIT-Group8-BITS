package com.syncstorm.hability.ui.home

import com.syncstorm.hability.database.GoalsModelClass
import com.syncstorm.hability.database.TaskModelClass
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeController {

    val today = LocalDateTime.now()
    val todayDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    fun getLatestTask(allTasks: MutableList<TaskModelClass>): TaskModelClass {
        val todayTasks: MutableList<TaskModelClass> = ArrayList()
        for (i in 0 until allTasks.size) {
            if (allTasks[i].taskStartDate == todayDate && allTasks[i].taskStatus == "Active") {
                val todayTask = TaskModelClass()
                todayTask.taskID = allTasks[i].taskID
                todayTask.taskName = allTasks[i].taskName
                todayTask.taskDescription = allTasks[i].taskDescription
                todayTask.taskStatus = allTasks[i].taskStatus
                todayTask.taskCategory = allTasks[i].taskCategory
                todayTask.taskStartDate = allTasks[i].taskStartDate
                todayTask.taskStartTime = allTasks[i].taskStartTime
                todayTask.taskEndDate  = allTasks[i].taskEndDate
                todayTask.taskEndTime = allTasks[i].taskEndTime
                todayTasks.add(todayTask)
            }
        }
        return TaskModelClass(
            todayTasks[0].taskID,
            todayTasks[0].taskName,
            todayTasks[0].taskDescription,
            todayTasks[0].taskStatus,
            todayTasks[0].taskCategory,
            todayTasks[0].taskStartDate,
            todayTasks[0].taskStartTime,
            todayTasks[0].taskEndDate,
            todayTasks[0].taskEndTime
        )
    }

    fun getLatestGoal(allGoals: MutableList<GoalsModelClass>): GoalsModelClass {
        val todayGoals: MutableList<GoalsModelClass> = ArrayList()
        for (i in 0 until allGoals.size) {
                val todayGoal = GoalsModelClass()
                todayGoal.goalID = allGoals[i].goalID
                todayGoal.goalTitle = allGoals[i].goalTitle
                todayGoal.goalDescription = allGoals[i].goalDescription
                todayGoal.goalDifficulty = allGoals[i].goalDifficulty
                todayGoal.goalCategory = allGoals[i].goalCategory
                todayGoal.goalStartDate = allGoals[i].goalStartDate
                todayGoals.add(todayGoal)
        }
        return GoalsModelClass(
            todayGoals[0].goalID,
            todayGoals[0].goalTitle,
            todayGoals[0].goalDescription,
            todayGoals[0].goalStartDate,
            todayGoals[0].goalDifficulty,
            todayGoals[0].goalCategory
        )
    }

    fun getSumAllGoals(allGoals: MutableList<GoalsModelClass>): String {
        var counter = 0
        for (i in allGoals.indices) {
            ++counter
        }
        return counter.toString()
    }

    fun getSumAllTasksToday(allTasks: MutableList<TaskModelClass>): String {
        var counter = 0
        for (i in allTasks.indices){
            if (allTasks[i].taskStatus == "Active" && allTasks[i].taskStartDate == todayDate) {
                ++counter
            }
        }
        return counter.toString()
    }
}