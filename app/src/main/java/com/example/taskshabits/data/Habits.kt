package com.example.taskshabits.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_habits")
data class Habits(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "TITLE") val cardTitle: String,
    @ColumnInfo(name = "DAYS_COUNT") val cardDaysCount: String,
    @ColumnInfo(name = "TAG") val cardTag: String
)
