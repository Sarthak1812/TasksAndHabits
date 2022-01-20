package com.example.taskshabits

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HabitsActivity : AppCompatActivity() {

    private lateinit var mHabitsViewModel: HabitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)



        val addHabitFAB = findViewById<FloatingActionButton>(R.id.fab_habits_add)

        // RecyclerView
        val habitsRV = findViewById<RecyclerView>(R.id.rv_habits)
        val habitsAdapter = HabitsAdapter()
        habitsRV.adapter = habitsAdapter
        habitsRV.layoutManager = LinearLayoutManager(this)


        mHabitsViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        mHabitsViewModel.allHabitsData.observe(this, Observer { habits ->
            habitsAdapter.setHabits(habits)
        })

        // sample data
//        val sampleData = ArrayList<Habits>()
//
//        sampleData.add(Habits(1, "WORKOUT", "10 days left", android.R.drawable.star_big_on))
//        sampleData.add(Habits(2, "WORKOUT", "10 days left", android.R.drawable.star_big_on))
//        sampleData.add(Habits(3, "WORKOUT", "10 days left", android.R.drawable.star_big_on))


        // TODO(Change habit dialog box to both Update and Add DATA .... !!!! )

        // habits card item onClick listener
        habitsAdapter.setOnHabitsItemClickListener(object : HabitsAdapter.OnHabitsItemClickListener{
            override fun onHabitsItemClick(position: Int) {

            }
        })

        addHabitFAB.setOnClickListener {
            val habitsDialogFragment = HabitsDialogFragment()
            habitsDialogFragment.show(supportFragmentManager, "Dialog")
//            insertDataToDb()
//            sampleData.add(Habits(4,"WORKOUT", "10 days left", android.R.drawable.star_big_on))
//            habitsAdapter.setHabits(sampleData)
        }




    }



}
