package com.example.task

import android.app.Application
import com.example.task.TaskApplication

class MainApplication : Application() {
    val taskApplication by lazy {
        TaskApplication(this)
    }

    override fun onCreate() {
        super.onCreate()
        taskApplication.open()
    }
}