package com.example.taskshabits.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.taskshabits.R
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class AddTasksFragment : Fragment() {

    private lateinit var titleET: TextInputLayout
    private lateinit var dateTimeTV: TextView
    private lateinit var dateTimeIB: ImageButton
    private lateinit var autoCompletePriorityTv: AutoCompleteTextView
    private lateinit var descET: TextInputLayout
    private lateinit var addBtn: Button
    private lateinit var cancelBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_tasks, container, false)

        initView(rootView)

        val currentDateTime = DateFormat.format("EE, MMM dd, yyyy hh:mm aa", Date())
        dateTimeTV.text = currentDateTime

        // Array Adapter using string-array
        val priorities = resources.getStringArray(R.array.tasks_priority_array)
        val prioritiesArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownmenu_item, priorities)
        autoCompletePriorityTv.setAdapter(prioritiesArrayAdapter)


        addBtn.setOnClickListener {
            insertTaskToDb()
        }

        cancelBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
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

    private fun insertTaskToDb() {

    }

}