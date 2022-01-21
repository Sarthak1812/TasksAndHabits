package com.example.taskshabits.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskshabits.data.Habits

@Dao
interface HabitsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addHabit(habits: Habits)

    @Query("SELECT * FROM table_habits ORDER BY id ASC")
    fun readAllHabits() : LiveData<List<Habits>>
}