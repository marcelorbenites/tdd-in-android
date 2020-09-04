package com.example.task.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val descriptionText = itemView.findViewById<TextView>(R.id.item_view_task)

    fun setText(description: String) {
        descriptionText.text = description
    }
}