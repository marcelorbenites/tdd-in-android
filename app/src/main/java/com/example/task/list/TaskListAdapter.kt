package com.example.task.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R

class TaskListAdapter(private val inflater: LayoutInflater, private val list: MutableList<String> = mutableListOf()) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TaskViewHolder(inflater.inflate(R.layout.item_view_task, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.setText(list[position])

    fun updateTasks(tasks: List<String>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }
}
