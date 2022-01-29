package com.example.taskshabits.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
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
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class ManageTasksFragment : Fragment() {


    private lateinit var mTasksViewModel: TasksViewModel

    private lateinit var titleManageET: TextInputLayout
    private lateinit var dateTimeManageTV: TextView
    private lateinit var dateTimeManageIB: ImageButton
    private lateinit var autoCompletePriorityManageTv: AutoCompleteTextView
    private lateinit var descManageET: TextInputLayout
    private lateinit var updateBtn: Button
    private lateinit var deleteBtn: Button

    private var taskId:Int = 0
    private lateinit var taskTitle: String
    private var taskDateTime: Long = 0
    private lateinit var taskDesc:String
    private lateinit var taskPriority: String

    private lateinit var taskDateTimeUpdated: Date


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_manage_tasks, container, false)

        initViews(rootView)

        mTasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        taskId = arguments?.getInt("Id", 0)!!
        taskTitle = arguments?.getString("title", "").toString()
        taskDateTime = arguments?.getLong("dateTime", 0)!!
        taskDesc = arguments?.getString("desc", "").toString()
        taskPriority = arguments?.getString("priority", "").toString()

        titleManageET.editText?.text = Editable.Factory.getInstance().newEditable(taskTitle)
        descManageET.editText?.text = Editable.Factory.getInstance().newEditable(taskDesc)
        taskDateTimeUpdated = Date(taskDateTime)
        dateTimeManageTV.text = DateFormat.format("EE, MMM dd, yyyy hh:mm aa", taskDateTimeUpdated)

        // Array Adapter using string-array for AutoCompleteTV
        autoCompletePriorityManageTv.text = Editable.Factory.getInstance().newEditable(taskPriority)
        val priorities = resources.getStringArray(R.array.tasks_priority_array)
        val prioritiesArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownmenu_item, priorities)
        autoCompletePriorityManageTv.setAdapter(prioritiesArrayAdapter)

        // pick date and time
        dateTimeManageIB.setOnClickListener {
            pickDateTime()
        }

        updateBtn.setOnClickListener {
            updateTaskInDb()
        }

        deleteBtn.setOnClickListener {
            deleteTask()
        }

        return rootView
    }

    private fun initViews(rootView: View) {
        titleManageET = rootView.findViewById(R.id.et_frag_manage_task_title)
        dateTimeManageTV = rootView.findViewById(R.id.tv_frag_manage_task_datetime)
        dateTimeManageIB = rootView.findViewById(R.id.ib_frag_task_manage_datetime)
        autoCompletePriorityManageTv = rootView.findViewById(R.id.autoComplete_manage_priority)
        descManageET = rootView.findViewById(R.id.et_frag_manage_task_desc)
        updateBtn = rootView.findViewById(R.id.btn_frag_manage_task_update)
        deleteBtn = rootView.findViewById(R.id.btn_frag_manage_task_delete)
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
                taskDateTimeUpdated = pickedDateTime.time
                dateTimeManageTV.text = DateFormat.format("EE, MMM dd, yyyy hh:mm aa", pickedDateTime)
            }, startHour, startMinute, false).show()

        }, startYear, startMonth, startDay).show()
    }

    private fun updateTaskInDb() {

        // Data from user
        val taskTitle = titleManageET.editText?.text.toString()
        val taskDesc = descManageET.editText?.text.toString()
        val prioritySelected = autoCompletePriorityManageTv.text.toString()

        if (!TextUtils.isEmpty(taskTitle) && !TextUtils.isEmpty(taskDesc)){

            // Add to database
            mTasksViewModel.updateTask(Tasks(taskId, taskTitle, taskDesc, taskDateTimeUpdated, prioritySelected))
            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_manageTasksFragment_to_tasksListFragment)
        }
        else if (TextUtils.isEmpty(taskTitle)) {
            titleManageET.isErrorEnabled = true
            titleManageET.error = "Incomplete field"
        }
        else if (TextUtils.isEmpty(taskDesc)) {
            descManageET.isErrorEnabled = true
            descManageET.error = "Incomplete field"
        }
        else {
            titleManageET.isErrorEnabled = true
            titleManageET.error = "Incomplete field"
            descManageET.isErrorEnabled = true
            descManageET.error = "Incomplete field"
        }

    }


    private fun deleteTask() {
        val alertBuilder = AlertDialog.Builder(requireContext())

        alertBuilder.setTitle("Delete Task - $taskTitle")
        alertBuilder.setMessage("Are you sure you want to delete")

        alertBuilder.setPositiveButton("YES"){ dialog , which ->
            // Delete Task from DB
            mTasksViewModel.deleteTask(Tasks(taskId, taskTitle, taskDesc, taskDateTimeUpdated, taskPriority))
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_manageTasksFragment_to_tasksListFragment)
        }

        alertBuilder.setNegativeButton("NO"){ dialog, which -> }

        alertBuilder.create().show()
    }

}