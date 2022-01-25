package com.example.taskshabits.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskshabits.data.MyDatabase
import com.example.taskshabits.data.Tasks
import com.example.taskshabits.data.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application):AndroidViewModel(application) {

    val allTasksData: LiveData<List<Tasks>>
    private val repository: TasksRepository

    init {
        val tasksDao = MyDatabase.getDatabase(application).tasksDao()
        repository = TasksRepository(tasksDao)
        allTasksData = repository.readAllTasksData
    }

    fun addTask(tasks: Tasks) = viewModelScope.launch(Dispatchers.IO) {
        repository.addTaskData(tasks)
    }

    fun updateTask(tasks: Tasks) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTaskData(tasks)
    }

    fun deleteTask(tasks: Tasks) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTaskData(tasks)
    }

}