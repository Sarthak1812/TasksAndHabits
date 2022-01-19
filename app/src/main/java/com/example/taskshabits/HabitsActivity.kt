package com.example.taskshabits

import android.app.Dialog
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HabitsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)

        val habitsRV = findViewById<RecyclerView>(R.id.rv_habits)
        habitsRV.layoutManager = LinearLayoutManager(this)

        val addHabitBtn = findViewById<FloatingActionButton>(R.id.fab_habits_add)

        // sample data
        val sampleData = ArrayList<Habits>()

        sampleData.add(Habits(1, "WORKOUT", "10 days left", android.R.drawable.star_big_on))
        sampleData.add(Habits(2, "WORKOUT", "10 days left", android.R.drawable.star_big_on))
        sampleData.add(Habits(3, "WORKOUT", "10 days left", android.R.drawable.star_big_on))

        val habitsAdapter = HabitsAdapter()
        habitsAdapter.setHabits(sampleData)
        habitsRV.adapter = habitsAdapter

        // habits card item onClick listener
        habitsAdapter.setOnHabitsItemClickListener(object : HabitsAdapter.OnHabitsItemClickListener{
            override fun onHabitsItemClick(position: Int) {
                habitsDialogCall()
            }

        })

        addHabitBtn.setOnClickListener {
            sampleData.add(Habits(4,"WORKOUT", "10 days left", android.R.drawable.star_big_on))
            habitsAdapter.setHabits(sampleData)
            Toast.makeText(this, "clicked fab", Toast.LENGTH_SHORT).show()
        }




    }

    fun habitsDialogCall(){

        val habitsDialog = HabitsDialog()


        // show dialog
        habitsDialog.show(supportFragmentManager, "Dialog")
    }


}
