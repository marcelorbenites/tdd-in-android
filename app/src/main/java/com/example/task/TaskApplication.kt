package com.example.task

import android.content.Context
import com.example.task.threading.HandlerScheduler
import com.example.task.threading.MainHandlerScheduler
import com.example.task.threading.Scheduler
import com.example.task.list.TaskListScreen
import com.example.task.save.SaveTaskScreen

class TaskApplication(
    context: Context,
    private val repository: TaskRepository = TaskRepository(context),
    private val scheduler: Scheduler = HandlerScheduler("task"),
    private val mainScheduler: Scheduler = MainHandlerScheduler
) {

    private object NoActionScreenCallback : (Screen) -> Unit {
        override fun invoke(screen: Screen) {}
    }

    private var screenCallback : (Screen) -> Unit = NoActionScreenCallback
    private lateinit var screen: Screen
    private val tasks = mutableListOf<String>()

    fun open() {
        scheduler.schedule {
            tasks.addAll(repository.getTasks())
            updateScreen(TaskListScreen(tasks))
        }
    }

    fun withScreenCallback(callback: (Screen) -> Unit) {
        scheduler.schedule {
            this.screenCallback = callback
            updateScreen(screen)
        }
    }

    fun addTask() {
        scheduler.schedule {
            updateScreen(SaveTaskScreen())
        }
    }

    fun saveTask(description: String) {
        scheduler.schedule {
            repository.saveTask(description)
            tasks.add(description)
            updateScreen(TaskListScreen(tasks))
        }
    }

    private fun updateScreen(screen: Screen) {
        this.screen = screen
        mainScheduler.schedule {
            screenCallback(this.screen)
        }
    }
}
