package com.example.taskshabits.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskshabits.R
import com.example.taskshabits.adapter.TasksAdapter
import com.example.taskshabits.util.TasksViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TasksListFragment : Fragment() {

    private lateinit var mTasksViewModel: TasksViewModel

    private lateinit var addTasksFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_tasks_list, container, false)

        initViews(rootView)

        // Recycler View Setup
        val tasksRV: RecyclerView = rootView.findViewById(R.id.rv_tasks)
        val tasksAdapter = TasksAdapter()
        tasksRV.adapter = tasksAdapter
        tasksRV.layoutManager = LinearLayoutManager(context)

        mTasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        mTasksViewModel.allTasksData.observe(viewLifecycleOwner, Observer { tasks ->
            tasksAdapter.setTasks(tasks)
        })





        addTasksFAB.setOnClickListener {
            findNavController().navigate(R.id.action_tasksListFragment_to_addTasksFragment)
        }

        return  rootView
    }


    private fun initViews(rootView: View) {
        addTasksFAB = rootView.findViewById(R.id.fab_tasks_add)

    }




}