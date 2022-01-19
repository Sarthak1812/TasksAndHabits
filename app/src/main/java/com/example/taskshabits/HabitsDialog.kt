package com.example.taskshabits

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.DialogFragment

class HabitsDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.habits_dialog, container, false)


        val doneDialogBtn = rootView.findViewById<Button>(R.id.btn_habit_dialog_done)
        val doneDialogTV = rootView.findViewById<TextView>(R.id.tv_habit_dialog_done)
        val doneContainerLL = rootView.findViewById<LinearLayout>(R.id.ll_habit_done_container)
        val closeDialogBtn = rootView.findViewById<ImageButton>(R.id.btn_habit_dialog_close)

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



        return rootView
    }

}