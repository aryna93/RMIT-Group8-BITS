package com.syncstorm.hability.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DATABASE_NAME = "Goals.db"
private const val DATABASE_VERSION = 1
private const val TABLE_NAME_GOALS = "goals"
private const val TABLE_NAME_USER_CREDENTIAL = "user_credential"

// Goals
private const val COLUMN_ID_GOAL = "goal_id"
private const val COLUMN_TITLE = "title"
private const val COLUMN_DESCRIPTION = "description"
private const val COLUMN_START_DATE = "start_date"
private const val COLUMN_DIFFICULTY = "difficulty"
private const val COLUMN_CATEGORY = "category"

// User Credential
private const val COLUMN_ID_USER = "user_id"
private const val COLUMN_USER_NAME = "name"
private const val COLUMN_USER_EMAIL = "email"

class DatabaseHelper(
    context: Context,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME_GOALS ($COLUMN_ID_GOAL INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_START_DATE DATE, $COLUMN_DIFFICULTY TEXT, $COLUMN_CATEGORY TEXT);")
        db?.execSQL("CREATE TABLE $TABLE_NAME_USER_CREDENTIAL ($COLUMN_ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_USER_NAME TEXT, $COLUMN_USER_EMAIL TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_GOALS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_USER_CREDENTIAL")
        onCreate(db)
    }

    // Goals
    fun addDataGoals(
        title: String,
        description: String,
        startDate: String,
        difficulty: String,
        category: String
    ) {
        var db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_DESCRIPTION, description)
        cv.put(COLUMN_START_DATE, startDate)
        cv.put(COLUMN_DIFFICULTY, difficulty)
        cv.put(COLUMN_CATEGORY, category)
        db.insert(TABLE_NAME_GOALS, null, cv)
    }

    fun readDataGoals(): Cursor? {
        val query =
            "SELECT * FROM $TABLE_NAME_GOALS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun updateDataGoals(
        row_id: String,
        title: String,
        description: String,
        startDate: String,
        difficulty: String,
        category: String
    ) {
        val db = writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_DESCRIPTION, description)
        cv.put(COLUMN_START_DATE, startDate)
        cv.put(COLUMN_DIFFICULTY, difficulty)
        cv.put(COLUMN_CATEGORY, category)

        db.update(TABLE_NAME_GOALS, cv, "$COLUMN_ID_GOAL = $row_id", null)
    }

    fun deleteDataGoals(row_id: String) {
        val db = writableDatabase
        db.delete(TABLE_NAME_GOALS, "$COLUMN_ID_GOAL = $row_id", null)
    }

    fun deleteDataGoalsAll(){
        val db = writableDatabase
        db.delete("$TABLE_NAME_GOALS", null, null)
    }


//    fun readDataGoalsDifficulty(row_id: String): Cursor?{
//        val query = "SELECT $COLUMN_DIFFICULTY WHERE $COLUMN_ID_GOAL = $row_id"
//        val db = this.readableDatabase
//        var cursor: Cursor? = null
//        if (db != null) {
//            cursor = db.rawQuery(query, null)
//        }
//        return cursor
//    }


    // User Credential
    fun addDataUserCredential(
        name: String,
        email: String
    ) {
        val db = this.writableDatabase
        val cv = ContentValues()

        val query = "SELECT count(*) FROM $TABLE_NAME_USER_CREDENTIAL"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        var counter = cursor.getInt(0)

        if (counter > 0) {
            cv.put(COLUMN_USER_NAME, name)
            cv.put(COLUMN_USER_EMAIL, email)
            db.update(TABLE_NAME_USER_CREDENTIAL, cv, "$COLUMN_ID_USER = 1", null)
        } else {
            cv.put(COLUMN_USER_NAME, name)
            cv.put(COLUMN_USER_EMAIL, email)
            db.insert(TABLE_NAME_USER_CREDENTIAL, null, cv)
        }
    }

    fun readDataUserDetails(): Cursor? {
        val query = "SELECT name, email FROM $TABLE_NAME_USER_CREDENTIAL"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

}