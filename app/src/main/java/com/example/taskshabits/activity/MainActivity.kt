package com.example.taskshabits.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.taskshabits.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val catHabitsBtn = findViewById<Button>(R.id.btn_cat_habits)
        catHabitsBtn.setOnClickListener {
            val intent = Intent(this, HabitsActivity::class.java)
            startActivity(intent)
        }

        val catTasksBtn = findViewById<Button>(R.id.btn_cat_tasks)
        catTasksBtn.setOnClickListener {
            val intent = Intent(this, TasksActivity::class.java)
            startActivity(intent)
        }
    }
}