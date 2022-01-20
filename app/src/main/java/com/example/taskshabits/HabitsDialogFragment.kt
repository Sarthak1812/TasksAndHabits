package com.example.taskshabits

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

class HabitsDialogFragment: DialogFragment(){

    private lateinit var mHabitsViewModel: HabitsViewModel

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

        mHabitsViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)



        // changing/replacing the DoneBtn onClick with DoneTextView
        doneDialogBtn.visibility = View.VISIBLE
        doneDialogTV.visibility = View.GONE
        doneDialogBtn.setOnClickListener {
            insertDataIntoDb()
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

    private fun insertDataIntoDb() {
        // TODO("Add Function for Input Check ")

        val data1 = Habits(0, "DATA1", "10 days to go", android.R.drawable.star_big_on)

        // Add to db
        mHabitsViewModel.addHabit(data1)
        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()


    }
}