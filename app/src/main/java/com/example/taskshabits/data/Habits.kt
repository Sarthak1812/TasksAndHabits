package com.example.taskshabits.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_habits")
data class Habits(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "TITLE") val cardTitle: String,
    @ColumnInfo(name= "DAYS_GOAL") val cardDayGoal: Int,
    @ColumnInfo(name = "DAYS_COMPLETED") val cardDaysCompleted: Int,
    @ColumnInfo(name = "DATE_LAST_COMPLETED") val cardDateLastCompleted: Date,
    @ColumnInfo(name = "TAG") val cardTag: String
)
