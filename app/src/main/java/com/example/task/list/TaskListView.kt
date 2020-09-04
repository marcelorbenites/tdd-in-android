package com.example.task.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.TaskApplication

class TaskListView : ConstraintLayout {

    lateinit var application: TaskApplication
    private lateinit var listView: RecyclerView

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun updateScreen(screen: TaskListScreen) {
        (listView.adapter as TaskListAdapter).updateTasks(screen.tasks)
    }

    private fun init() {
        inflate(context, R.layout.view_task_list, this)
        findViewById<Button>(R.id.view_task_list_add_task_button).setOnClickListener {
            application.addTask()
        }
        listView = findViewById(R.id.view_task_list_view)
        listView.layoutManager = LinearLayoutManager(context);
        listView.adapter = TaskListAdapter(LayoutInflater.from(context))
    }
}