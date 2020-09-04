package com.example.task

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.task.list.TaskListScreen
import com.example.task.list.TaskListView
import com.example.task.save.SaveTaskScreen
import com.example.task.save.SaveTaskView

class MainActivity : AppCompatActivity() {

    private val taskApplication by lazy {
        (application as MainApplication).taskApplication
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frame = findViewById<FrameLayout>(R.id.activity_main_frame)

        taskApplication.withScreenCallback { screen ->
            frame.removeAllViews()
            when (screen) {
                is TaskListScreen -> {
                    val view = TaskListView(this)
                    view.application = taskApplication
                    frame.addView(view)
                    view.updateScreen(screen)
                }
                is SaveTaskScreen -> {
                    val view = SaveTaskView(this)
                    view.application = taskApplication
                    frame.addView(view)
                }
            }
        }
    }
}
