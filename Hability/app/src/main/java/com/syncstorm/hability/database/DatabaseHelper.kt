package com.syncstorm.hability.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

private const val DATABASE_NAME = "Hability.db"
private const val DATABASE_VERSION = 1
private const val TABLE_NAME_GOALS = "goals"
private const val TABLE_NAME_TASKS = "tasks"

private const val COLUMN_ID_GOALS = "_id"
private const val COLUMN_TITLE_GOALS = "title"
private const val COLUMN_DESCRIPTION_GOALS = "description"
private const val COLUMN_DATE_GOALS = "_date"
private const val COLUMN_TIME_GOALS = "_time"
private const val COLUMN_TAG_GOALS = "tag"

private const val COLUMN_ID_TASKS = "_id"
private const val COLUMN_TITLE_TASKS = "title"
private const val COLUMN_DESCRIPTION_TASKS = "description"
private const val COLUMN_DATE_TASKS = "_date"
private const val COLUMN_TIME_TASKS = "_time"
private const val COLUMN_TAG_TASKS = "tag"


private val context: Context? = null

class DatabaseHelper(
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val goals =
            "CREATE TABLE $TABLE_NAME_GOALS ($COLUMN_ID_GOALS INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE_GOALS TEXT, $COLUMN_DESCRIPTION_GOALS TEXT, $COLUMN_DATE_GOALS DATE, $COLUMN_TIME_GOALS TIME, $COLUMN_TAG_GOALS TEXT);"

        val tasks =
            "CREATE TABLE $TABLE_NAME_TASKS ($COLUMN_ID_TASKS INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE_TASKS TEXT, $COLUMN_DESCRIPTION_TASKS TEXT, $COLUMN_DATE_TASKS DATE, $COLUMN_TIME_TASKS TIME, $COLUMN_TAG_TASKS TEXT);"

        db?.execSQL(goals)
        db?.execSQL(tasks)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_GOALS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_TASKS")
        onCreate(db)
    }

    fun addGoal(title: String, description: String, date: String, time: String, tag: String) {
        var db = writableDatabase
        var cv = ContentValues()

        cv.put(COLUMN_TITLE_GOALS, title)
        cv.put(COLUMN_DESCRIPTION_GOALS, description)
        cv.put(COLUMN_DATE_GOALS, date)
        cv.put(COLUMN_TIME_GOALS, time)
        cv.put(COLUMN_TAG_GOALS, tag)
        var result = db.insert(TABLE_NAME_GOALS, null, cv)

        // to test add method
        if (result.equals(-1)) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
        }
    }


    fun addTask(title: String, description: String, date: String, time: String, tag: String) {
        var db = writableDatabase
        var cv = ContentValues()

        cv.put(COLUMN_TITLE_TASKS, title)
        cv.put(COLUMN_DESCRIPTION_TASKS, description)
        cv.put(COLUMN_DATE_TASKS, date)
        cv.put(COLUMN_TIME_TASKS, time)
        cv.put(COLUMN_TAG_TASKS, tag)
        var result = db.insert(TABLE_NAME_TASKS, null, cv)

        // to test add method
        if (result.equals(-1)) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
        }
    }
}