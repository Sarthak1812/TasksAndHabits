package com.example.taskshabits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val catHabitsBtn = findViewById<Button>(R.id.btn_cat_habits)
        catHabitsBtn.setOnClickListener {
            val toast = Toast.makeText(this, "ButtonClicked", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}