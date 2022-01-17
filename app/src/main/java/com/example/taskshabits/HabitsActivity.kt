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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HabitsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)

        val habitsRV = findViewById<RecyclerView>(R.id.rv_habits)
        habitsRV.layoutManager = LinearLayoutManager(this)

        // sample data
        val sampleData = ArrayList<HabitsItemViewModel>()

        for (i in 1..5){
            sampleData.add(HabitsItemViewModel("WORKOUT", "10 days left", android.R.drawable.star_big_on))
        }

        val habitsAdapter = HabitsAdapter(sampleData)
        habitsRV.adapter = habitsAdapter


/*         row/card onClick listener
        val habitCard = findViewById<CardView>(R.id.cv_habits)
        habitCard.setOnClickListener {
            // creating dialog
            val habitDialog = Dialog(this)
            habitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            habitDialog.setCancelable(false)
            habitDialog.setContentView(R.layout.habits_dialog)

            // init
            val doneDialogBtn = habitDialog.findViewById<Button>(R.id.btn_habit_dialog_done)
            val doneDialogTV = habitDialog.findViewById<TextView>(R.id.tv_habit_dialog_done)
            val closeDialogBtn = habitDialog.findViewById<ImageButton>(R.id.btn_habit_dialog_close)

            // changing/replacing the DoneBtn onClick with DoneTextView
            doneDialogBtn.visibility = View.VISIBLE
            doneDialogTV.visibility = View.GONE
            doneDialogBtn.setOnClickListener {
                doneDialogTV.visibility = View.VISIBLE
                doneDialogBtn.visibility = View.GONE
            }

            // onClick - Close ImageButton
            closeDialogBtn.setOnClickListener {
                habitDialog.dismiss()
                Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show()
            }

            // show dialog
            habitDialog.show()
        } */
    }
}