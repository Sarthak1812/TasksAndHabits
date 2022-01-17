package com.example.taskshabits

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class HabitsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)

        val habitCard = findViewById<CardView>(R.id.cv_habits)
        habitCard.setOnClickListener {
            // creating dialog
            val habitDialog = Dialog(this)
            habitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            habitDialog.setCancelable(false)
            habitDialog.setContentView(R.layout.habits_dialog)

            val doneDialogBtn = habitDialog.findViewById<Button>(R.id.btn_habit_dialog_done)
            val doneDialogTV = habitDialog.findViewById<TextView>(R.id.tv_habit_dialog_done)
            doneDialogBtn.visibility = View.VISIBLE
            doneDialogTV.visibility = View.GONE

            doneDialogBtn.setOnClickListener {
                doneDialogTV.visibility = View.VISIBLE
                doneDialogBtn.visibility = View.GONE
            }

            val closeDialogBtn = habitDialog.findViewById<ImageButton>(R.id.btn_habit_dialog_close)
            closeDialogBtn.setOnClickListener {
                habitDialog.dismiss()
                Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show()
            }
            habitDialog.show()
        }
    }
}