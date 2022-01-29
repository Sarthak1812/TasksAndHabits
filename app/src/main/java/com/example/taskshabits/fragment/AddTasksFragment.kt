package com.example.taskshabits.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taskshabits.R
import com.example.taskshabits.data.Tasks
import com.example.taskshabits.util.TasksViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class AddTasksFragment : Fragment() {

    private lateinit var mTasksViewModel: TasksViewModel

    private lateinit var titleET: TextInputLayout
    private lateinit var dateTimeTV: TextView
    private lateinit var dateTimeIB: ImageButton
    private lateinit var autoCompletePriorityTv: AutoCompleteTextView
    private lateinit var descET: TextInputLayout
    private lateinit var addBtn: MaterialButton
    private lateinit var cancelBtn: MaterialButton

    private var taskDateTime: Date = Date()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_tasks, container, false)

        initView(rootView)

        mTasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        // set current time and date initially
        dateTimeTV.text = DateFormat.format("EE, MMM dd, yyyy hh:mm aa", taskDateTime)

        // Array Adapter using string-array
        val priorities = resources.getStringArray(R.array.tasks_priority_array)
        val prioritiesArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownmenu_item, priorities)
        autoCompletePriorityTv.setAdapter(prioritiesArrayAdapter)

        // pick date and time
        dateTimeIB.setOnClickListener {
            pickDateTime()
        }

        addBtn.setOnClickListener {
            insertTaskToDb()
        }

        cancelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addTasksFragment_to_tasksListFragment)
        }

        return rootView
    }

    private fun initView(rootView: View) {
        titleET = rootView.findViewById(R.id.et_frag_add_task_title)
        dateTimeTV = rootView.findViewById(R.id.tv_frag_add_task_datetime)
        dateTimeIB = rootView.findViewById(R.id.ib_frag_task_add_datetime)
        autoCompletePriorityTv = rootView.findViewById(R.id.autoComplete_priority)
        descET = rootView.findViewById(R.id.et_frag_add_task_desc)
        addBtn = rootView.findViewById(R.id.btn_frag_add_task_add)
        cancelBtn = rootView.findViewById(R.id.btn_frag_add_task_cancel)
    }


    private fun pickDateTime() {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        // DatePicker
        DatePickerDialog(requireContext(), { _, year, month, day ->

            // TimePicker
            TimePickerDialog(requireContext(), { _, hour, minute ->

                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                // set to textView
                taskDateTime = pickedDateTime.time
                dateTimeTV.text = DateFormat.format("EE, MMM dd, yyyy hh:mm aa", pickedDateTime)
            }, startHour, startMinute, false).show()

        }, startYear, startMonth, startDay).show()
    }



    private fun insertTaskToDb() {

        // Data from user
        val taskTitle = titleET.editText?.text.toString()
        val taskDesc = descET.editText?.text.toString()
        val prioritySelected = autoCompletePriorityTv.text.toString()

        if (!TextUtils.isEmpty(taskTitle) && !TextUtils.isEmpty(taskDesc)){

            // Add to database
            mTasksViewModel.addTask(Tasks(0, taskTitle, taskDesc, taskDateTime, prioritySelected))
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addTasksFragment_to_tasksListFragment)
        }
        else if (TextUtils.isEmpty(taskTitle)) {
            titleET.isErrorEnabled = true
            titleET.error = "Incomplete field"
        }
        else if (TextUtils.isEmpty(taskDesc)) {
            descET.isErrorEnabled = true
            descET.error = "Incomplete field"
        }
        else {
            titleET.isErrorEnabled = true
            titleET.error = "Incomplete field"
            descET.isErrorEnabled = true
            descET.error = "Incomplete field"
        }

    }

}