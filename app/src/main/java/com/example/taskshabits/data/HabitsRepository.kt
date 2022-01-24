package com.example.taskshabits.data

import androidx.lifecycle.LiveData
import com.example.taskshabits.data.Habits
import com.example.taskshabits.data.HabitsDao

class HabitsRepository(private val habitsDao: HabitsDao) {

    val readAllHabitsData: LiveData<List<Habits>> = habitsDao.readAllHabits()

    fun addHabitData(habits: Habits){
        habitsDao.addHabit(habits)
    }

    fun updateHabitData(habits: Habits){
        habitsDao.updateHabit(habits)
    }

    fun deleteHabitData(habits: Habits){
        habitsDao.deleteHabit(habits)
    }
}