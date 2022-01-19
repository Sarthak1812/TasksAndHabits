package com.example.taskshabits

import androidx.lifecycle.LiveData

class HabitsRepository(private val habitsDao: HabitsDao) {

    val readAllHabitsData: LiveData<List<Habits>> = habitsDao.readAllHabits()

    suspend fun addHabitData(habits: Habits){
        habitsDao.addHabit(habits)
    }

}