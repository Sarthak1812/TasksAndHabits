package com.example.taskshabits.fragment

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.taskshabits.R
import com.example.taskshabits.data.Habits
import com.example.taskshabits.util.HabitsViewModel

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
        val tagAnimLottie = rootView.findViewById<LottieAnimationView>(R.id.lottie_anim_view_tag)
        val autoCompleteTagsTv = rootView.findViewById<AutoCompleteTextView>(R.id.autoComplete_tags)
        val doneDialogBtn = rootView.findViewById<Button>(R.id.btn_habit_dialog_done)
        val doneDialogTV = rootView.findViewById<TextView>(R.id.tv_habit_dialog_done)
        val doneContainerLL = rootView.findViewById<LinearLayout>(R.id.ll_habit_done_container)
        val closeDialogBtn = rootView.findViewById<ImageButton>(R.id.btn_habit_dialog_close)

        mHabitsViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)

        // Array Adapter using string-array
        val tags = resources.getStringArray(R.array.habits_tag_array)
        val tagsArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownmenu_item, tags)
        autoCompleteTagsTv.setAdapter(tagsArrayAdapter)
        autoCompleteTagsTv.setOnItemClickListener { parent, view, position, id ->
            setTagAnimation(autoCompleteTagsTv.text.toString(), tagAnimLottie)
        }

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

    private fun setTagAnimation(tagChoice: String, animTagView: LottieAnimationView) {
        when(tagChoice) {
            "Workout/Fitness" -> animTagView.setAnimation(R.raw.tag_workout)
            "Work/Task" -> animTagView.setAnimation(R.raw.tag_work)
            "Yoga/Meditation" -> animTagView.setAnimation(R.raw.tag_yoga)
            "Default-Target" -> animTagView.setAnimation(R.raw.tag_default_target)
            else -> {
                animTagView.setAnimation(R.raw.tag_default_target)
            }
        }
        animTagView.playAnimation()
    }


    private fun insertDataIntoDb() {
        // TODO("Add Function for Input Check ")

        val data2 = Habits(0, "DATA1", "10 days to go", "Default-Target")

        // Add to db
        mHabitsViewModel.addHabit(data2)
        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()


    }
}