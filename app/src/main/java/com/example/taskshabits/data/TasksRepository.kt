package com.example.taskshabits.data

import androidx.lifecycle.LiveData

class TasksRepository(private val tasksDao: TasksDao) {

    val readAllTasksData: LiveData<List<Tasks>> = tasksDao.readAllTasks()

    fun addTaskData(tasks: Tasks){
        tasksDao.addTask(tasks)
    }

    fun updateTaskData(tasks: Tasks){
        tasksDao.updateTask(tasks)
    }

    fun deleteTaskData(tasks: Tasks){
        tasksDao.deleteTask(tasks)
    }

}