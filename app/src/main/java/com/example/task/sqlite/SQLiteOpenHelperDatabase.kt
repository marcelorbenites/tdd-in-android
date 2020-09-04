package com.example.task.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteOpenHelperDatabase(context: Context, name: String?, version: Int, private val createSQL: String) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(createSQL)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}
