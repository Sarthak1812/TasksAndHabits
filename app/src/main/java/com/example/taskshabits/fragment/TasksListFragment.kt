package com.example.taskshabits.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskshabits.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TasksListFragment : Fragment() {

    private lateinit var addTasksFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_tasks_list, container, false)

        addTasksFAB = rootView.findViewById(R.id.fab_tasks_add)

        addTasksFAB.setOnClickListener {
            findNavController().navigate(R.id.action_tasksListFragment_to_addTasksFragment)
        }

        return  rootView
    }


}