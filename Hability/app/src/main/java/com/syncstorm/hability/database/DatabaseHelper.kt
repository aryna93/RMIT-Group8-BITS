package com.syncstorm.hability.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DATABASE_NAME = "Hability.db"
private const val DATABASE_VERSION = 1
private const val TABLE_NAME_TASKS = "tasks"
private const val TABLE_NAME_USER_DATA = "data"

private const val COLUMN_ID = "task_id"
private const val COLUMN_TITLE = "title"
private const val COLUMN_DESCRIPTION = "description"
private const val COLUMN_START_DATE = "start_date"
private const val COLUMN_END_DATE = "end_date"
private const val COLUMN_TIME = "time"
private const val COLUMN_CATEGORY = "category"

private const val COLUMN_ID_USER = "user_id"
private const val COLUMN_USER_NAME = "name"
private const val COLUMN_USER_EMAIL = "email"

class DatabaseHelper(
    context: Context,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME_TASKS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_START_DATE DATE, $COLUMN_END_DATE DATE, $COLUMN_TIME TIME, $COLUMN_CATEGORY TEXT);")
        db?.execSQL("CREATE TABLE $TABLE_NAME_USER_DATA ($COLUMN_ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_USER_NAME TEXT, $COLUMN_USER_EMAIL TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_TASKS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_USER_DATA")
        onCreate(db)
    }

    fun addDataTasks(
        title: String,
        description: String,
        startDate: String,
        endDate: String,
        time: String,
        category: String
    ) {
        var db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_DESCRIPTION, description)
        cv.put(COLUMN_START_DATE, startDate)
        cv.put(COLUMN_END_DATE, endDate)
        cv.put(COLUMN_TIME, time)
        cv.put(COLUMN_CATEGORY, category)
        db.insert(TABLE_NAME_TASKS, null, cv)
    }

    fun readDataTasks(): Cursor? {
        val query =
            "SELECT title, description, start_date, end_date, time, category FROM $TABLE_NAME_TASKS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun updateDataTasks(
        row_id: String,
        title: String,
        description: String,
        startDate: String,
        endDate: String,
        time: String,
        category: String
    ) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_DESCRIPTION, description)
        cv.put(COLUMN_START_DATE, startDate)
        cv.put(COLUMN_END_DATE, endDate)
        cv.put(COLUMN_TIME, time)
        cv.put(COLUMN_CATEGORY, category)

        db.update(TABLE_NAME_TASKS, cv, "$COLUMN_ID = $row_id", null)

    }

    fun deleteDataTasks(row_id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME_TASKS, "$COLUMN_ID = $row_id", null)
    }

    fun addDataUserDetails(
        name: String,
        email: String
    ) {


        val db = this.writableDatabase
        val cv = ContentValues()

        val query = "SELECT count(*) FROM $TABLE_NAME_USER_DATA"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        var counter = cursor.getInt(0)

        if (counter > 0) {
            cv.put(COLUMN_USER_NAME, name)
            cv.put(COLUMN_USER_EMAIL, email)
            db.update(TABLE_NAME_USER_DATA, cv, "$COLUMN_ID_USER = 1", null)
        } else {
            cv.put(COLUMN_USER_NAME, name)
            cv.put(COLUMN_USER_EMAIL, email)
            db.insert(TABLE_NAME_USER_DATA, null, cv)
        }


    }

    fun readDataUserDetails(): Cursor? {
        val query = "SELECT name, email FROM $TABLE_NAME_USER_DATA"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

}