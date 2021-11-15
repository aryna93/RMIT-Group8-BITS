package com.syncstorm.hability.database

data class TaskModelClass(
    var taskID: Int? = null,
    var taskName : String? = null,
    var taskDescription: String? = null,
    var taskStatus: String? = null,
    var taskCategory: String? = null,
    var taskStartDate: String? = null,
    var taskStartTime: String? = null,
    var taskEndDate: String? = null,
    var taskEndTime: String? = null
)
