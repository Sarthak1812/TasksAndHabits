package com.example.taskshabits

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Habits::class], version = 1, exportSchema = false)
abstract class HabitsDatabase : RoomDatabase() {

    abstract fun habitsDao() : HabitsDao

    companion object {

        @Volatile
        private val INSTANCE: HabitsDatabase? = null



    }

}