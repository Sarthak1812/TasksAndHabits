package com.example.taskshabits.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "TITLE") val title: String,
    @ColumnInfo(name = "DESCRIPTION") val description: String,
    @ColumnInfo(name = "DUE_DATE") val dueDate: Date,
    @ColumnInfo(name = "PRIORITY") val priority: String
)