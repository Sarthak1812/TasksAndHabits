package com.example.taskshabits

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.Window
import android.widget.*

class HabitsDialog(context: Context) : Dialog(context) {

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.habits_dialog)

        // init
        val doneDialogBtn = findViewById<Button>(R.id.btn_habit_dialog_done)
        val doneDialogTV = findViewById<TextView>(R.id.tv_habit_dialog_done)
        val doneContainerLL = findViewById<LinearLayout>(R.id.ll_habit_done_container)
        val closeDialogBtn = findViewById<ImageButton>(R.id.btn_habit_dialog_close)

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
            dismiss()
            Toast.makeText(context, "Closed", Toast.LENGTH_SHORT).show()
        }

        // show dialog
    }
}