package com.example.taskshabits.data

import android.content.Context
import androidx.room.*
import com.example.taskshabits.util.DateTypeConverter

@Database(entities = [Habits::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class HabitsDatabase : RoomDatabase() {

    abstract fun habitsDao() : HabitsDao

    companion object {

        @Volatile
        private var INSTANCE: HabitsDatabase? = null

        fun getDatabase(context: Context): HabitsDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitsDatabase::class.java,
                    "db_habits"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }

}