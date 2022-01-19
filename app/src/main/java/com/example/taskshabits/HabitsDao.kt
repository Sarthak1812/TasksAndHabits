package com.example.taskshabits

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HabitsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHabit(habits: Habits)

    @Query("SELECT * FROM table_habits ORDER BY id ASC")
    suspend fun readAllHabits() : LiveData<List<Habits>>
}