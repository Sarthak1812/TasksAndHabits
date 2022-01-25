package com.example.taskshabits.util

import androidx.recyclerview.widget.DiffUtil
import com.example.taskshabits.data.Tasks

class TasksDiffCallback(
    private val oldList: List<Tasks>,
    private val newList: List<Tasks>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}