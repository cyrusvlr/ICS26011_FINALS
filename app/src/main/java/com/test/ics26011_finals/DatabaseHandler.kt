package com.test.ics26011_finals

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "FitnessDatabase"
        private val TABLE_USER_INFO = "UserInfoTable"
        const val KEY_ID_USER_INFO = "id" //id
        private val KEY_GOALS = "goals"
        private val KEY_HEIGHT = "height"
        private val KEY_WEIGHT = "weight"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USER_INFO_TABLE = ("CREATE TABLE " + TABLE_USER_INFO + "(" +
                KEY_ID_USER_INFO + " INTEGER PRIMARY KEY AUTOINCREMENT," + //id
                KEY_GOALS + " TEXT," +
                KEY_HEIGHT + " FLOAT," +
                KEY_WEIGHT + " FLOAT)")
        db?.execSQL(CREATE_USER_INFO_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO)
        onCreate(db)
    }

    //add
    fun addUserData(empModelClass: EmpModelClass, id: Long = -1): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_GOALS, empModelClass.userGoals)
            put(KEY_HEIGHT, empModelClass.userHeight)
            put(KEY_WEIGHT, empModelClass.userWeight)
        }

        val success: Long
        if (id == -1L) {
            // Insert a new row
            success = db.insert(TABLE_USER_INFO, null, contentValues)
        } else {
            // Update existing row with the given ID
            success = db.update(
                TABLE_USER_INFO,
                contentValues,
                "$KEY_ID_USER_INFO=?",
                arrayOf(id.toString())
            ).toLong()
        }
        db.close()

        return success
    }

    //view
    @SuppressLint("Range")
    fun viewUserData(): List<EmpModelClass> {
        val empList: ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()
        val selectQuery = "SELECT * FROM $TABLE_USER_INFO"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var userInfoID: Int
        var userGoals: String
        var userHeight: Float
        var userWeight: Float

        if (cursor.moveToFirst()) {
            do {

                userInfoID = cursor.getInt(cursor.getColumnIndex("id"))
                userGoals = cursor.getString(cursor.getColumnIndex("goals"))
                userHeight = cursor.getFloat(cursor.getColumnIndex("height"))
                userWeight = cursor.getFloat(cursor.getColumnIndex("weight"))

                val emp = EmpModelClass(
                    userInfoID = userInfoID,
                    userGoals = userGoals,
                    userHeight = userHeight,
                    userWeight = userWeight
                )
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    //update
    fun updateUserData(empModelClass: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_GOALS, empModelClass.userGoals)
            put(KEY_HEIGHT, empModelClass.userHeight)
            put(KEY_WEIGHT, empModelClass.userWeight)
        }

        val whereClause = "$KEY_ID_USER_INFO = ?"
        val whereArgs = arrayOf(empModelClass.userInfoID.toString())

        val success = db.update(TABLE_USER_INFO, contentValues, whereClause, whereArgs)
        db.close()
        return success
    }

}
