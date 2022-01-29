package com.example.taskshabits.fragment

import android.os.Bundle
import android.text.TextUtils
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
import com.google.android.material.textfield.TextInputLayout
import java.util.*

// Creating New Habit and adding to database fragment
class AddHabitsDialogFragment: DialogFragment(){

    private lateinit var mHabitsViewModel: HabitsViewModel

    private lateinit var tagAnimLottie: LottieAnimationView
    private lateinit var autoCompleteTagsTv: AutoCompleteTextView
    private lateinit var addHabitBtn: Button
    private lateinit var closeDialogIB: ImageButton
    private lateinit var titleEt:TextInputLayout
    private lateinit var daysMaxCountEt:TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_habits_dialog, container, false)

        // init view components
        initViews(rootView)


        // Array Adapter using string-array
        val tags = resources.getStringArray(R.array.habits_tag_array)
        val tagsArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownmenu_item, tags)
        autoCompleteTagsTv.setAdapter(tagsArrayAdapter)
        autoCompleteTagsTv.setOnItemClickListener { parent, view, position, id ->
            setTagAnimation(autoCompleteTagsTv.text.toString(), tagAnimLottie)
        }

        // changing/replacing the DoneBtn onClick with DoneTextView
        addHabitBtn.setOnClickListener {
            insertDataIntoDb()
        }

        // onClick - Close ImageButton
        closeDialogIB.setOnClickListener {
            dismiss()
        }


        return rootView
    }

    private fun initViews(rootView: View) {
        titleEt = rootView.findViewById(R.id.et_habits_title)
        daysMaxCountEt = rootView.findViewById(R.id.et_habits_days_max_count)
        tagAnimLottie = rootView.findViewById(R.id.lottie_anim_view_tag)
        autoCompleteTagsTv = rootView.findViewById(R.id.autoComplete_tags)
        addHabitBtn = rootView.findViewById(R.id.btn_habit_dialog_add)
        closeDialogIB = rootView.findViewById(R.id.btn_habit_dialog_close)
        mHabitsViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
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

        // Data from user
        val habitTitle = titleEt.editText?.text.toString()
        val habitMaxDayString = daysMaxCountEt.editText?.text.toString()
        val tagSelected = autoCompleteTagsTv.text.toString()

        if (!TextUtils.isEmpty(habitTitle) && habitMaxDayString != "")
        {

            val habitMaxDay = Integer.parseInt(habitMaxDayString)

            // Add to database
            mHabitsViewModel.addHabit(Habits(0, habitTitle, habitMaxDay, 0, Date(), tagSelected))
            Toast.makeText(context, "Habit created", Toast.LENGTH_SHORT).show()

            dismiss()

        }
        else if (TextUtils.isEmpty(habitTitle) && habitMaxDayString == "")
        {
            titleEt.isErrorEnabled = true
            daysMaxCountEt.isErrorEnabled = true
            titleEt.error = "Invalid"
            daysMaxCountEt.error = "Invalid"
        }
        else if (TextUtils.isEmpty(habitTitle))
        {
            titleEt.isErrorEnabled = true
            titleEt.error = "Invalid"
        }
        else if (habitMaxDayString == "")
        {
            daysMaxCountEt.isErrorEnabled = true
            daysMaxCountEt.error = "Invalid"
        }

     

    }


}