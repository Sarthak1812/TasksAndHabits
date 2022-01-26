package com.example.taskshabits.data

import android.content.Context
import androidx.room.*
import com.example.taskshabits.util.DateTypeConverter

@Database(entities = [Habits::class, Tasks::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun habitsDao() : HabitsDao
    abstract fun tasksDao() : TasksDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "database_tasks_habits"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }

}