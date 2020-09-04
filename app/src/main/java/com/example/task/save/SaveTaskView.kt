package com.example.task.save

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.task.R
import com.example.task.TaskApplication

class SaveTaskView : ConstraintLayout {

    lateinit var application: TaskApplication
    lateinit var screen: SaveTaskScreen
    private lateinit var saveTaskButton: Button
    private lateinit var descriptionInputField: EditText

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_add_task, this)
        saveTaskButton = findViewById(R.id.view_add_task_save_task_button)
        descriptionInputField = findViewById(R.id.view_add_task_description_input_field)
        saveTaskButton.setOnClickListener {
            application.saveTask(descriptionInputField.text.toString())
        }
    }
}
