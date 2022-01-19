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
                HabitsDialog(this@HabitsActivity).show()
            }

        })

        addHabitBtn.setOnClickListener {
            sampleData.add(Habits(4,"WORKOUT", "10 days left", android.R.drawable.star_big_on))
            habitsAdapter.setHabits(sampleData)
            Toast.makeText(this, "clicked fab", Toast.LENGTH_SHORT).show()
        }




    }
/*
    fun habitsDialogCall(){
        //row/card onClick listener
        // creating dialog
        val habitsDialog = Dialog(this)
        habitsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        habitsDialog.setCancelable(false)
        habitsDialog.setContentView(R.layout.habits_dialog)

        // init
        val doneDialogBtn = habitsDialog.findViewById<Button>(R.id.btn_habit_dialog_done)
        val doneDialogTV = habitsDialog.findViewById<TextView>(R.id.tv_habit_dialog_done)
        val doneContainerLL = habitsDialog.findViewById<LinearLayout>(R.id.ll_habit_done_container)
        val closeDialogBtn = habitsDialog.findViewById<ImageButton>(R.id.btn_habit_dialog_close)

        // changing/replacing the DoneBtn onClick with DoneTextView
        doneDialogBtn.visibility = View.VISIBLE
        doneDialogTV.visibility = View.GONE
        doneDialogBtn.setOnClickListener {
            TransitionManager.beginDelayedTransition(doneContainerLL)
            doneDialogTV.visibility = View.VISIBLE
            doneDialogBtn.visibility = View.GONE
        }

        // onClick - Close ImageButton
        closeDialogBtn.setOnClickListener {
            habitsDialog.dismiss()
            Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show()
        }

        // show dialog
        habitsDialog.show()
    }
*/

}
