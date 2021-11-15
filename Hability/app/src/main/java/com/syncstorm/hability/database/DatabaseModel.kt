package com.syncstorm.hability.database

data class DatabaseModel (
    val DATABASENAME: String = "HABILITYDB",
    val TABLENAME: String = "Task",
    val COL_ID: String = "id",
    val COL_NAME: String = "name",
    val COL_DESCRIPTION: String = "description",
    val COL_STATUS: String = "status",
    val COL_STARTDATE: String = "startdate",
    val COL_ENDDATE: String = "enddate",
    val COL_STARTTIME: String = "starttime",
    val COL_ENDTIME: String = "endtime",
    val COL_CATEGORY: String = "taskcategory"
)