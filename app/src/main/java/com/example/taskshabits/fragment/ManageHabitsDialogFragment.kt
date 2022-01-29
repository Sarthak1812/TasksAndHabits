package com.example.taskshabits.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.transition.TransitionManager
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


// Updating, deleting habit fragment
class ManageHabitsDialogFragment : DialogFragment(){

    private lateinit var mHabitsViewModel: HabitsViewModel

    private lateinit var tagAnimLottie: LottieAnimationView
    private lateinit var autoCompleteTagsTv: AutoCompleteTextView
    private lateinit var doneDialogBtn: Button
    private lateinit var doneDialogTV : TextView
    private lateinit var allDoneTV: TextView
    private lateinit var closeDialogIB: ImageButton
    private lateinit var updateBtn: Button
    private lateinit var updateDialogIB: ImageButton
    private lateinit var deleteDialogIB: ImageButton
    private lateinit var btnContainerLL: LinearLayout
    private lateinit var titleEt: TextInputLayout
    private lateinit var daysCompletedCountEt: TextView
    private lateinit var progressBarHabit: ProgressBar

    private var habitId:Int = 0
    private lateinit var title: String
    private var daysComplete: Int = 0
    private var daysGoal: Int = 0
    private var dateStored: Long = 0
    private lateinit var habitTag: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_manage_habits_dialog, container, false)

        // init view components
        initViews(rootView)

        habitId = arguments?.getInt("Id", 0)!!
        title = arguments?.getString("title", "").toString()
        daysGoal = arguments?.getInt("daysGoal", 0)!!
        daysComplete = arguments?.getInt("daysCompleted", 0)!!
        dateStored = arguments?.getLong("dateStored", 0)!!
        habitTag = arguments?.getString("tag", "").toString()

        titleEt.editText?.text = Editable.Factory.getInstance().newEditable(title)
        daysCompletedCountEt.text = daysComplete.toString()
        autoCompleteTagsTv.text = Editable.Factory.getInstance().newEditable(habitTag)
        setTagAnimation(habitTag, tagAnimLottie)
        val habitProgressDiv = daysComplete.toDouble()/daysGoal
        progressBarHabit.progress = (habitProgressDiv*100).toInt()

        if(habitProgressDiv==1.0)
        {
            doneDialogBtn.visibility = View.GONE
            doneDialogTV.visibility = View.GONE
            allDoneTV.visibility = View.VISIBLE
            updateBtn.visibility = View.GONE
        }
        else
            taskCompleteCheck()

        // to get the coming midnight time
        val calendar =  Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time


        doneDialogBtn.setOnClickListener {
            updateDB(Habits(habitId, title, daysGoal, daysComplete+1, tomorrow, habitTag))
            TransitionManager.beginDelayedTransition(btnContainerLL)
            doneDialogTV.visibility = View.VISIBLE
            doneDialogBtn.visibility = View.GONE
        }

        updateDialogIB.setOnClickListener{
            TransitionManager.beginDelayedTransition(btnContainerLL)
            doneDialogBtn.visibility = View.GONE
            doneDialogTV.visibility = View.GONE
            allDoneTV.visibility = View.GONE
            updateBtn.visibility = View.VISIBLE
            updateHabit()
        }

        deleteDialogIB.setOnClickListener {
            deleteHabit()
        }

        // onClick - Close ImageButton
        closeDialogIB.setOnClickListener {
            dismiss()
        }

        return rootView
    }

    private fun initViews(rootView: View) {
        titleEt = rootView.findViewById(R.id.et_habits_title)
        daysCompletedCountEt = rootView.findViewById(R.id.et_habits_days_completed)
        tagAnimLottie = rootView.findViewById(R.id.lottie_anim_view_tag)
        autoCompleteTagsTv = rootView.findViewById(R.id.autoComplete_tags)
        doneDialogBtn = rootView.findViewById(R.id.btn_habit_dialog_add)
        doneDialogTV = rootView.findViewById(R.id.tv_habit_dialog_done)
        allDoneTV = rootView.findViewById(R.id.tv_habit_all_done)
        closeDialogIB = rootView.findViewById(R.id.ib_habit_dialog_close)
        updateBtn = rootView.findViewById(R.id.btn_habit_dialog_update)
        updateDialogIB = rootView.findViewById(R.id.ib_habit_dialog_update)
        deleteDialogIB = rootView.findViewById(R.id.ib_habit_dialog_delete)
        btnContainerLL = rootView.findViewById(R.id.ll_habit_btn_container)
        progressBarHabit = rootView.findViewById(R.id.progress_habit_days)
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

    private fun taskCompleteCheck() {

        val currentTime = Date()
        val dbDate = Date(dateStored)


        // changing/replacing the DoneBtn onClick with DoneTextView
        if (currentTime.after(dbDate)){
            doneDialogBtn.visibility = View.VISIBLE
            doneDialogTV.visibility = View.GONE
            allDoneTV.visibility = View.GONE
            updateBtn.visibility = View.GONE
        }
        else{
            doneDialogBtn.visibility = View.GONE
            doneDialogTV.visibility = View.VISIBLE
            allDoneTV.visibility = View.GONE
            updateBtn.visibility = View.GONE
        }


    }


    private fun updateDB(habits: Habits) {
        mHabitsViewModel.updateHabit(habits)
        Toast.makeText(context, "Updated DB", Toast.LENGTH_SHORT).show()
        dismiss()
    }


    private fun updateHabit() {
        // Array Adapter using string-array
        val tags = resources.getStringArray(R.array.habits_tag_array)
        val tagsArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownmenu_item, tags)
        autoCompleteTagsTv.setAdapter(tagsArrayAdapter)
        autoCompleteTagsTv.setOnItemClickListener { parent, view, position, id ->
            setTagAnimation(autoCompleteTagsTv.text.toString(), tagAnimLottie)
        }

        updateBtn.setOnClickListener {
            // Data from user
            val habitTitle = titleEt.editText?.text.toString()
            if (!TextUtils.isEmpty(habitTitle)) {
                val updateHabits = Habits(habitId, habitTitle, daysGoal, daysComplete, Date(), autoCompleteTagsTv.text.toString())
                updateDB(updateHabits)
            }
            else
            {
                titleEt.isErrorEnabled = true
                titleEt.error = "Invalid"
            }
        }
    }

    private fun deleteHabit() {

        val alertBuilder = AlertDialog.Builder(requireContext())

        alertBuilder.setTitle("Delete Habit - $title")
        alertBuilder.setMessage("Are you sure you want to delete")

        alertBuilder.setPositiveButton("YES"){ dialog , which ->
            // Delete Habit from DB
            mHabitsViewModel.deleteHabit(Habits(habitId, title, daysGoal, daysComplete, Date(dateStored), habitTag))
            Toast.makeText(context, "Deleted Habit", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        alertBuilder.setNegativeButton("NO"){ dialog, which -> }

        alertBuilder.create().show()
    }


}

