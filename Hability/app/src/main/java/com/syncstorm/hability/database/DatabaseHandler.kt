package com.syncstorm.hability.database
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, "HABILITYDB", null,
    1) {
    private val dbModel = DatabaseModel()
    private val tableName = dbModel.TABLENAME
    // val dbName = dbModel.DATABASENAME

    override fun onCreate(db: SQLiteDatabase?) {

        val createTaskTable = "CREATE TABLE " +
                dbModel.TABLENAME + " (" +
                dbModel.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                dbModel.COL_NAME + " VARCHAR(256)," +
                dbModel.COL_DESCRIPTION + " VARCHAR(256)," +
                dbModel.COL_STATUS + " VARCHAR(256)," +
                dbModel.COL_STARTDATE + " VARCHAR(256)," +
                dbModel.COL_ENDDATE + " VARCHAR(256)," +
                dbModel.COL_STARTTIME + " VARCHAR(256)," +
                dbModel.COL_ENDTIME + " VARCHAR(256)," +
                dbModel.COL_CATEGORY + " VARCHAR(256) )"
        db?.execSQL(createTaskTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertTask(task: TaskModelClass) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(dbModel.COL_NAME, task.taskName)
        contentValues.put(dbModel.COL_DESCRIPTION, task.taskDescription)
        contentValues.put(dbModel.COL_STATUS, task.taskStatus)
        contentValues.put(dbModel.COL_STARTDATE, task.taskStartDate)
        contentValues.put(dbModel.COL_ENDDATE, task.taskEndDate)
        contentValues.put(dbModel.COL_STARTTIME, task.taskStartTime)
        contentValues.put(dbModel.COL_ENDTIME, task.taskEndTime)
        contentValues.put(dbModel.COL_CATEGORY, task.taskCategory)
        val result = database.insert(tableName, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed. Please try again...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readTask(): MutableList<TaskModelClass> {
        val list: MutableList<TaskModelClass> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $tableName"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val task = TaskModelClass()
                task.taskID = result.getString(result.getColumnIndex(dbModel.COL_ID)).toInt()
                task.taskName = result.getString(result.getColumnIndex(dbModel.COL_NAME))
                task.taskDescription = result.getString(result.getColumnIndex(dbModel.COL_DESCRIPTION))
                task.taskStatus = result.getString(result.getColumnIndex(dbModel.COL_STATUS))
                task.taskStartDate = result.getString(result.getColumnIndex(dbModel.COL_STARTDATE))
                task.taskEndDate = result.getString(result.getColumnIndex(dbModel.COL_ENDDATE))
                task.taskStartTime = result.getString(result.getColumnIndex(dbModel.COL_STARTTIME))
                task.taskEndTime = result.getString(result.getColumnIndex(dbModel.COL_ENDTIME))
                task.taskCategory= result.getString(result.getColumnIndex(dbModel.COL_CATEGORY))
                list.add(task)
                }
            while (result.moveToNext())
            }
        return list
        }

    fun deleteTask(task: TaskModelClass) {
        val db = this.writableDatabase
        db.delete(dbModel.TABLENAME, task.taskID.toString(), null)
    }

}