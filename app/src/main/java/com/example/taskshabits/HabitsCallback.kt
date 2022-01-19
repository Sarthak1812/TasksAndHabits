package com.example.taskshabits

import androidx.recyclerview.widget.DiffUtil

class HabitsCallback(
    private val oldList: List<Habits>,
    private val newList: List<Habits>
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
//        return when {
//            oldList[oldItemPosition].id != newList[newItemPosition].id -> {false}
//
//            // oldList[oldItemPosition].cardTitle != newList[newItemPosition].cardTitle -> {false}
//            // Image and daysCount can be same
//            // oldList[oldItemPosition].cardDaysCount != newList[newItemPosition].cardDaysCount -> {false}
//            // oldList[oldItemPosition].cardImage != newList[newItemPosition].cardImage -> {false}
//
//            else -> true
//        }
    }
}