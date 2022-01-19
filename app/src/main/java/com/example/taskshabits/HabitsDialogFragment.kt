package com.example.taskshabits

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment

class HabitsDialogFragment: DialogFragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_habits_dialog, container, false)

        // init
        val titleEt = rootView.findViewById<EditText>(R.id.et_habits_title)
        val daysCountEt = rootView.findViewById<EditText>(R.id.et_habits_days_count)
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