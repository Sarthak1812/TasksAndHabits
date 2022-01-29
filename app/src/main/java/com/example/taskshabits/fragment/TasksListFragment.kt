package com.example.taskshabits.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        mTasksViewModel.allTasksData.observe(viewLifecycleOwner, { tasks ->
            tasksAdapter.setTasks(tasks)
        })


        // tasks onClick listener
        tasksAdapter.mTasksItemClick = { tasks ->

            // Bundle to pass data
            val bundle = Bundle()
            bundle.putInt("Id", tasks.id)
            bundle.putString("title", tasks.title)
            bundle.putLong("dateTime", tasks.dueDate.time)
            bundle.putString("desc", tasks.description)
            bundle.putString("priority", tasks.priority)

            findNavController().navigate(R.id.action_tasksListFragment_to_manageTasksFragment, bundle)

        }

        // checkbox onClick listener = task completed
        tasksAdapter.checkBoxOnClick = { tasks ->

            // Deleting completed task
            mTasksViewModel.deleteTask(tasks)

            Toast.makeText(context, "🎉 Task Completed 👏🏻", Toast.LENGTH_SHORT).show()

        }


        addTasksFAB.setOnClickListener {
            findNavController().navigate(R.id.action_tasksListFragment_to_addTasksFragment)
        }

        return  rootView
    }


    private fun initViews(rootView: View) {
        addTasksFAB = rootView.findViewById(R.id.fab_tasks_add)

    }





}