package com.example.task

import android.content.Context
import android.database.Cursor
import com.example.task.sqlite.SQLiteOpenHelperDatabase

class TaskRepository(context: Context) {
    private val database by lazy {
        SQLiteOpenHelperDatabase(
            context,
            "task_database",
            1,
            "CREATE TABLE IF NOT EXISTS tasks (description TEXT)")
    }


    fun getTasks(): List<String> {
        val tasks = mutableListOf<String>()
        var cursor: Cursor? = null
        try {
            cursor = database
                .readableDatabase
                .rawQuery("SELECT * FROM tasks", null)
            while (cursor.moveToNext()) {
                tasks.add(cursor
                    .getString(cursor.getColumnIndex("description")))
            }
        } finally {
            cursor?.close()
        }
        return tasks
    }

    fun saveTask(description: String) {
        database
            .writableDatabase
            .execSQL("INSERT INTO tasks (description) VALUES ('$description')")
    }
}