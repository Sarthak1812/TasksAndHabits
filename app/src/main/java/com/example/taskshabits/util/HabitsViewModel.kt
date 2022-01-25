package com.example.taskshabits.util

import android.app.Application
import androidx.lifecycle.*
import com.example.taskshabits.data.Habits
import com.example.taskshabits.data.MyDatabase
import com.example.taskshabits.data.HabitsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitsViewModel(application: Application):AndroidViewModel(application) {

    val allHabitsData: LiveData<List<Habits>>
    private val repository: HabitsRepository

    init {
        val habitsDao = MyDatabase.getDatabase(application).habitsDao()
        repository = HabitsRepository(habitsDao)
        allHabitsData = repository.readAllHabitsData
    }

    fun addHabit(habits: Habits) = viewModelScope.launch(Dispatchers.IO){
            repository.addHabitData(habits)
    }

    fun updateHabit(habits: Habits) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateHabitData(habits)
    }

    fun deleteHabit(habits: Habits) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteHabitData(habits)
    }

}
