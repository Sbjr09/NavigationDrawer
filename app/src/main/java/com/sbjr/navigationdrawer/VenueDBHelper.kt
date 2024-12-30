package com.sbjr.navigationdrawer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.Collator.PRIMARY
import android.os.FileObserver.CREATE

class VenueDBHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "matches.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "saved_matches"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID TEXT PRIMARY KEY,
                $COLUMN_NAME TEXT
            )
        """.trimIndent()
            db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun saveMatch(id: String, name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, id)
            put(COLUMN_NAME, name)
        }
        db.insert(TABLE_NAME, null, values)
    }

    fun isMatchSaved(id: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(id))
        val isSaved = cursor.moveToFirst()
        cursor.close()
        return isSaved
    }
}



