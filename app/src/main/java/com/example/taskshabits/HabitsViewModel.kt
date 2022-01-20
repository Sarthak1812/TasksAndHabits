package com.example.taskshabits

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitsViewModel(application: Application):AndroidViewModel(application) {

    val allHabitsData: LiveData<List<Habits>>
    private val repository: HabitsRepository

    init {
        val habitsDao = HabitsDatabase.getDatabase(application).habitsDao()
        repository = HabitsRepository(habitsDao)
        allHabitsData = repository.readAllHabitsData
    }

    fun addHabit(habits: Habits) = viewModelScope.launch(Dispatchers.IO){
            repository.addHabitData(habits)
    }


}
