package com.example.taskshabits.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskshabits.R
import com.example.taskshabits.data.Tasks
import com.example.taskshabits.util.TasksDiffCallback
import com.google.android.material.checkbox.MaterialCheckBox
import android.text.format.DateFormat

class TasksAdapter :RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    var mTasksList = emptyList<Tasks>()

    var mTasksItemClick: ((Tasks)-> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the layout_card_tasks
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card_tasks, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tasksItemViewModel = mTasksList[position]
        holder.taskTitle.text = tasksItemViewModel.title
        holder.taskDate.text = DateFormat.format("EEEE, MMM, dd, yyyy", tasksItemViewModel.dueDate)
        setTaskPriorityColor(holder, tasksItemViewModel.priority)
    }

    private fun setTaskPriorityColor(holder: ViewHolder, priority: String) {
        when(priority){
            "HIGH" -> holder.taskPriority.setBackgroundColor(Color.parseColor("#FF0000"))
            "MEDIUM" -> holder.taskPriority.setBackgroundColor(Color.parseColor("#008080"))
            "LOW" -> holder.taskPriority.setBackgroundColor(Color.parseColor("#999999"))

            else -> {
                holder.taskPriority.setBackgroundColor(Color.parseColor("#689F38"))
            }
        }
    }

    override fun getItemCount(): Int {
        return mTasksList.size
    }

    fun setTasks(tasks : List<Tasks>) {
        val diffUtil = TasksDiffCallback(mTasksList, tasks)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.mTasksList = tasks
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val taskCheckbox: MaterialCheckBox = itemView.findViewById(R.id.mcb_task_card_checkbox)
        val taskTitle: TextView = itemView.findViewById(R.id.tv_tasks_card_title)
        val taskDate: TextView = itemView.findViewById(R.id.tv_tasks_card_date)
        val taskPriority: View = itemView.findViewById(R.id.view_task_priority)

        // on item click
        init {
            itemView.setOnClickListener{
                mTasksItemClick?.invoke(mTasksList[adapterPosition])
            }
        }
    }


}