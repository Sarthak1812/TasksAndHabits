package com.example.taskshabits.data

import androidx.lifecycle.LiveData
import com.example.taskshabits.data.Habits
import com.example.taskshabits.data.HabitsDao

class HabitsRepository(private val habitsDao: HabitsDao) {

    val readAllHabitsData: LiveData<List<Habits>> = habitsDao.readAllHabits()

    suspend fun addHabitData(habits: Habits){
        habitsDao.addHabit(habits)
    }

}