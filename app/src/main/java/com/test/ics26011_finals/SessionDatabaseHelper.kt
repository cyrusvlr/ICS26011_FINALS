package com.test.ics26011_finals

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SessionDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "fitboundapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allsessions"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_START_TIME = "startTime"
        private const val COLUMN_END_TIME = "endTime"
        private const val COLUMN_CONTENT = "content"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_START_TIME TEXT, $COLUMN_END_TIME TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertSession(session: Session) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, session.name)
            put(COLUMN_START_TIME, session.startTime)
            put(COLUMN_END_TIME, session.endTime)
            put(COLUMN_CONTENT, session.content)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllSessions(): List<Session> {
        val sessionsList = mutableListOf<Session>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val startTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_START_TIME))
            val endTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_END_TIME))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val session = Session(id, name, startTime, endTime, content)
            sessionsList.add(session)
        }
        cursor.close()
        db.close()
        return sessionsList
    }

    fun updateSession(session: Session) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, session.name)
            put(COLUMN_START_TIME, session.startTime)
            put(COLUMN_END_TIME, session.endTime)
            put(COLUMN_CONTENT, session.content)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgument = arrayOf(session.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgument)
        db.close()
    }

    fun getSessionByID(sessionId: Int): Session {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $sessionId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
        val startTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_START_TIME))
        val endTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_END_TIME))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return Session(id, name, startTime, endTime, content)
    }

    fun deleteSession(sessionId: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgument = arrayOf(sessionId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgument)
        db.close()
    }
}