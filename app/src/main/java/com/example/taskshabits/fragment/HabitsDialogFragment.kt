package com.example.taskshabits.fragment

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.taskshabits.R
import com.example.taskshabits.util.HabitsViewModel

class HabitsDialogFragment: DialogFragment(){

    private lateinit var mHabitsViewModel: HabitsViewModel

    private lateinit var tagAnimLottie: LottieAnimationView
    private lateinit var autoCompleteTagsTv: AutoCompleteTextView
    private lateinit var doneDialogBtn: Button
    private lateinit var doneDialogTV : TextView
    private lateinit var closeDialogIB: ImageButton
    private lateinit var doneContainerLL: LinearLayout
    private lateinit var titleEt:EditText
    private lateinit var daysCompletedCountEt:EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_habits_dialog, container, false)

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
        doneDialogBtn.visibility = View.VISIBLE
        doneDialogTV.visibility = View.GONE
        doneDialogBtn.setOnClickListener {
            insertDataIntoDb()
            TransitionManager.beginDelayedTransition(doneContainerLL)
            doneDialogTV.visibility = View.VISIBLE
            doneDialogBtn.visibility = View.GONE
        }

        // onClick - Close ImageButton
        closeDialogIB.setOnClickListener {
            dismiss()
            Toast.makeText(context, "Closed", Toast.LENGTH_SHORT).show()
        }


        return rootView
    }

    private fun initViews(rootView: View) {
        titleEt = rootView.findViewById(R.id.et_habits_title)
        daysCompletedCountEt = rootView.findViewById(R.id.et_habits_days_compl_count)
        tagAnimLottie = rootView.findViewById(R.id.lottie_anim_view_tag)
        autoCompleteTagsTv = rootView.findViewById(R.id.autoComplete_tags)
        doneDialogBtn = rootView.findViewById(R.id.btn_habit_dialog_done)
        doneDialogTV = rootView.findViewById(R.id.tv_habit_dialog_done)
        closeDialogIB = rootView.findViewById(R.id.btn_habit_dialog_close)
        doneContainerLL = rootView.findViewById(R.id.ll_habit_done_container)
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

//        val data2 = Habits(0, "DATA1", "10 days to go", "Default-Target")

        // Add to db
//        mHabitsViewModel.addHabit(data2)
        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()


    }
}