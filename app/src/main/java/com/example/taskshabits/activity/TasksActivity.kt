package com.example.taskshabits.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.taskshabits.R

class TasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fcv_tasks)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}